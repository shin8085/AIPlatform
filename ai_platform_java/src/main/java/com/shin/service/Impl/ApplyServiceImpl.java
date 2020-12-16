package com.shin.service.Impl;

import com.shin.service.ApplyService;
import com.shin.utils.AIInterface;
import com.shin.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
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

    /**
     * 人脸识别
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @Override
    public Result faceDetection(MultipartFile file) throws IOException {
        Map<String, Object> resultMap = AIInterface.request("/face/recognize",file);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> boxes=(List<List<Object>>)resultMap.get("boxes");
//            List<List<Object>> point_2d_array=(List<List<Object>>)resultMap.get("point_2d_array");
            if(boxes.isEmpty()){
                return Result.error("未检测到人脸");
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            for(int i=0;i<boxes.size();i++){
                int x1=(int)boxes.get(i).get(0);
                int y1=(int)boxes.get(i).get(1);
                int x2=(int)boxes.get(i).get(2);
                int y2=(int)boxes.get(i).get(3);
//                List<Object> points=point_2d_array.get(i);
//                for(int j=0;j<points.size()-1;j+=2){
//                    System.out.println((int)points.get(j)+","+(int)points.get(j+1));
//                    graphics.fillOval((int)points.get(j),(int)points.get(j+1),5,5);
//                }
                graphics.setStroke(new BasicStroke(3.0f));
                graphics.setColor(Color.red);
                graphics.drawRect(x1,y1,Math.abs(x1-x2),Math.abs(y1-y2));
            }
            long time = new Date().getTime();
            File outputFile = new File(tempImagePath +time+".jpg");
            ImageIO.write(image,"jpg",outputFile);
            return Result.success(tempImageRelativePath +time+".jpg");
        }
        return Result.error("未检测到人脸");
    }

    /**
     * 年龄检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @Override
    public Result ageEstimation(MultipartFile file) throws IOException {
        Map<String, Object> resultMap = AIInterface.request("/age/age_estimation",file);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> points=(List<List<Object>>)resultMap.get("boxes");
            List<List<Object>> ages=(List<List<Object>>)resultMap.get("result");
            if(points.isEmpty()){
                return Result.error("未检测到人脸");
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            Graphics graphics = image.getGraphics();
            for(int i=0;i<points.size();i++){
                int x1=(int)points.get(i).get(0);
                int y1=(int)points.get(i).get(1);
                double age=(double)ages.get(i).get(0);
                graphics.setFont(new Font("SimSun",Font.BOLD,image.getWidth()/10));
                graphics.drawString(String.valueOf((int)age)+"岁",x1,y1);
            }
            long time = new Date().getTime();
            File outputFile = new File(tempImagePath +time+".jpg");
            ImageIO.write(image,"jpg",outputFile);
            return Result.success(tempImageRelativePath +time+".jpg");
        }
        return Result.error("未检测到人脸");
    }

    /**
     * 烟雾检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @Override
    public Result smokeDetection(MultipartFile file) throws IOException {
        Map<String, Object> resultMap = AIInterface.request("/smoke_detection/smoke_detection",file);
        System.out.println(resultMap);
        double confidences= (double) ((List<Object>)resultMap.get("confidences")).get(0);
        NumberFormat numberFormat=NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result=numberFormat.format(confidences*100);
        long time = new Date().getTime();
        file.transferTo(new File(tempImagePath +time+".jpg"));
        Map<String,String> data=new HashMap<>();
        data.put("path",tempImageRelativePath +time+".jpg");
        data.put("confidences",result);
        return Result.success("success",data);
    }

    /**
     * 性别检测
     * @param file 图片
     * @return result
     */
    @Override
    public Result genderDetection(MultipartFile file) throws IOException {
        Map<String, Object> resultMap = AIInterface.request("/gender_detection/gender_detection",file);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> points=(List<List<Object>>)resultMap.get("box");
            List<String> genders=(List<String>)resultMap.get("gender");
            if(points.isEmpty()){
                return Result.error("未检测到人脸");
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            Graphics graphics = image.getGraphics();
            for(int i=0;i<points.size();i++){
                int x1=(int)points.get(i).get(0);
                int y1=(int)points.get(i).get(1);
                String g=genders.get(i);
                graphics.setColor(Color.black);
                graphics.setFont(new Font("SimSun",Font.BOLD,image.getWidth()/12));
                graphics.drawString(g,x1,y1);
            }
            long time = new Date().getTime();
            File outputFile = new File(tempImagePath +time+".jpg");
            ImageIO.write(image,"jpg",outputFile);
            return Result.success(tempImageRelativePath +time+".jpg");
        }
        return Result.error("未检测到人脸");
    }
}
