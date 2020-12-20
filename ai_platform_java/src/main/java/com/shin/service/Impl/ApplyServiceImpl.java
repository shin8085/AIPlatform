package com.shin.service.Impl;

import com.shin.service.ApplyService;
import com.shin.utils.AIInterface;
import com.shin.utils.ImageTran;
import com.shin.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceImpl implements ApplyService {

    /**临时图片真实地址*/
    @Value("${image.upload.tmp-path}")
    private String tempImagePath;

    /**临时图片显示相对地址*/
    @Value("${image.upload.tmp-path.relative}")
    private String tempImageRelativePath;

    //目标检测结果
    static String [] ods={"person","bicycle","car","motorbike","aeroplane","bus","train","truck","boat","traffic light","fire hydrant","stop sign","parking meter","bench","bird","cat","dog","horse","sheep","cow","elephant","bear","zebra","giraffe","backpack","umbrella","handbag","tie","suitcase","frisbee","skis","snowboard","sports ball","kite","baseball bat","baseball glove","skateboard","surfboard","tennis racket","bottle","wine glass","cup","fork","knife","spoon","bowl","banana","apple","sandwich","orange","broccoli","carrot","hot dog","pizza","donut","cake","chair","sofa","pottedplant","bed","diningtable","toilet","tvmonitor","laptop","mouse","remote","keyboard","cell phone","microwave","oven","toaster","sink","refrigerator","book","clock","vase","scissors","teddy bear","hair drier","toothbrush"};

    static Map<String,String> driver_status=new HashMap<>();

    static{
        driver_status.put("normal driving","安全驾驶");
        driver_status.put("texting-right","右手打字");
        driver_status.put("talking on the phone-right","右手打电话");
        driver_status.put("texting-left","左手打字");
        driver_status.put("talking on the phone-left","左手打电话");
        driver_status.put("operation the radio","调收音机");
        driver_status.put("drinking","喝饮料");
        driver_status.put("reaching behind","拿后面的东西");
        driver_status.put("hair and makeup","整理头发和化妆");
        driver_status.put("talking to passenger","和其他乘客说话");
    }

    /**
     * 人脸识别
     * @param base64Data 图片的base64编码
     * @return Result
     */
    @Override
    public Result faceDetection(String base64Data){
        Map<String, Object> resultMap = AIInterface.request("/face/recognize",base64Data);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> boxes=(List<List<Object>>)resultMap.get("boxes");
            BufferedImage image = ImageTran.base64ToBufferedImage(base64Data);
            if(boxes.isEmpty()||image==null){
                return Result.error("未检测到人脸");
            }
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            for(int i=0;i<boxes.size();i++){
                int x1=(int)boxes.get(i).get(0);
                int y1=(int)boxes.get(i).get(1);
                int x2=(int)boxes.get(i).get(2);
                int y2=(int)boxes.get(i).get(3);
                graphics.setStroke(new BasicStroke(3.0f));
                graphics.setColor(Color.red);
                graphics.drawRect(x1,y1,Math.abs(x1-x2),Math.abs(y1-y2));
            }
            Map<String,Object> response=new HashMap<>();
            response.put("base64Data",ImageTran.BufferedImageToBase64(image));
            return Result.success("",response);
        }
        return Result.error("未检测到人脸");
    }

    /**
     * 年龄检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    @Override
    public Result ageEstimation(String base64Data){
        Map<String, Object> resultMap = AIInterface.request("/age/age_estimation",base64Data);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> points=(List<List<Object>>)resultMap.get("boxes");
            List<List<Object>> ages=(List<List<Object>>)resultMap.get("result");
            BufferedImage image = ImageTran.base64ToBufferedImage(base64Data);
            if(points.isEmpty()||image==null){
                return Result.error("未检测到人脸");
            }
            Graphics graphics = image.getGraphics();
            for(int i=0;i<points.size();i++){
                int x1=(int)points.get(i).get(0);
                int y1=(int)points.get(i).get(1);
                double age=(double)ages.get(i).get(0);
                graphics.setFont(new Font("SimSun",Font.BOLD,image.getWidth()/10));
                graphics.drawString(String.valueOf((int)age)+"岁",x1,y1);
            }
            Map<String,Object> response=new HashMap<>();
            response.put("base64Data",ImageTran.BufferedImageToBase64(image));
            return Result.success("",response);
        }
        return Result.error("未检测到人脸");
    }

    /**
     * 目标检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    @Override
    public Result objectDetection(String base64Data){
        Map<String, Object> resultMap = AIInterface.request("/object_detection/object_detection",base64Data);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> points=(List<List<Object>>)resultMap.get("boxes");
            List<Integer> result=(List<Integer>)resultMap.get("result");
            BufferedImage image = ImageTran.base64ToBufferedImage(base64Data);
            if(points.isEmpty()||image==null){
                return Result.error("未检测到物体");
            }
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            for(int i=0;i<points.size();i++){
                int x1=(int)points.get(i).get(0);
                int y1=(int)points.get(i).get(1);
                int x2=(int)points.get(i).get(2);
                int y2=(int)points.get(i).get(3);
                int r=result.get(i);
                graphics.setColor(Color.red);
                Font font=new Font("SimSun",Font.BOLD,image.getWidth()/12);
                graphics.setFont(font);
                FontMetrics fontMetrics = graphics.getFontMetrics(font);
                int font_w=fontMetrics.stringWidth(ods[r]); //获取文字宽度
                graphics.drawString(ods[r], (x1+x2)/2-font_w/2,(y1+y2)/2); //文字居中显示
                graphics.drawRect(x1,y1,Math.abs(x1-x2),Math.abs(y1-y2));
            }
            Map<String,Object> response=new HashMap<>();
            response.put("base64Data",ImageTran.BufferedImageToBase64(image));
            return Result.success("",response);
        }
        return Result.error("未检测到物体");
    }

    /**
     * 烟雾检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    @Override
    public Result smokeDetection(String base64Data){
        Map<String, Object> resultMap = AIInterface.request("/smoke_detection/smoke_detection",base64Data);
        System.out.println(resultMap);
        double confidences= (double) ((List<Object>)resultMap.get("confidences")).get(0);
        NumberFormat numberFormat=NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result=numberFormat.format(confidences*100);
        Map<String,String> response=new HashMap<>();
        response.put("base64Data",base64Data);
        response.put("confidences",result);
        return Result.success("",response);
    }

    /**
     * 驾驶员状态检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    @Override
    public Result distractedDriverDetection(String base64Data){
        Map<String, Object> resultMap = AIInterface.request("/distracted_driver_detection/distracted_driver_detection",base64Data);
        System.out.println(resultMap);
        double confidences= (double) ((List<Object>)resultMap.get("confidences")).get(0);
        String status= ((List<String>) resultMap.get("result")).get(0);
        NumberFormat numberFormat=NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result=numberFormat.format(confidences);
        Map<String,String> data=new HashMap<>();
        data.put("base64Data",base64Data);
        data.put("confidences",result);
        data.put("status",driver_status.get(status));
        return Result.success("",data);
    }

    /**
     * 口罩检测
     * @param base64Data 图片的base64编码
     * @return Result
     */
    @Override
    public Result maskDetection(String base64Data){
        Map<String, Object> resultMap = AIInterface.request("/mask_detection/mask_detection",base64Data);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> points=(List<List<Object>>)resultMap.get("boxes");
            List<List<String>> result=(List<List<String>>)resultMap.get("result");
            BufferedImage image = ImageTran.base64ToBufferedImage(base64Data);
            if(points.isEmpty()||image==null){
                return Result.error("未检测到人脸");
            }
            Graphics graphics = image.getGraphics();
            for(int i=0;i<points.size();i++){
                int x1=(int)points.get(i).get(0);
                int y1=(int)points.get(i).get(1);
                String r=result.get(i).get(0);
                graphics.setColor(Color.red);
                graphics.setFont(new Font("SimSun",Font.BOLD,image.getWidth()/12));
                graphics.drawString(r,x1,y1);
            }
            Map<String,Object> response=new HashMap<>();
            response.put("base64Data",ImageTran.BufferedImageToBase64(image));
            return Result.success("",response);
        }
        return Result.error("未检测到人脸");
    }

    /**
     * 性别检测
     * @param base64Data 图片的base64编码
     * @return result
     */
    @Override
    public Result genderDetection(String base64Data) {
        Map<String, Object> resultMap = AIInterface.request("/gender_detection/gender_detection",base64Data);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> points=(List<List<Object>>)resultMap.get("box");
            List<String> genders=(List<String>)resultMap.get("gender");
            BufferedImage image = ImageTran.base64ToBufferedImage(base64Data);
            if(points.isEmpty()||image==null){
                return Result.error("未检测到人脸");
            }
            Graphics graphics = image.getGraphics();
            for(int i=0;i<points.size();i++){
                int x1=(int)points.get(i).get(0);
                int y1=(int)points.get(i).get(1);
                String g=genders.get(i);
                graphics.setColor(Color.black);
                graphics.setFont(new Font("SimSun",Font.BOLD,image.getWidth()/12));
                graphics.drawString(g,x1,y1);
            }
            Map<String,Object> response=new HashMap<>();
            response.put("base64Data",ImageTran.BufferedImageToBase64(image));
            return Result.success("",response);
        }
        return Result.error("未检测到人脸");
    }
}
