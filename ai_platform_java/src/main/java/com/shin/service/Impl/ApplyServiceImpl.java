package com.shin.service.Impl;

import com.shin.service.ApplyService;
import com.shin.utils.AIInterface;
import com.shin.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
            Object boxes = resultMap.get("boxes");
            Object result=resultMap.get("result");
            List<List<Object>> points=(List<List<Object>>)boxes;
            List<List<Object>> ages=(List<List<Object>>)result;
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
     * 性别检测
     * @param file 图片
     * @return result
     */
    @Override
    public Result genderDetection(MultipartFile file) throws IOException {
        Map<String, Object> resultMap = AIInterface.request("/gender_detection/gender_detection",file);
        System.out.println(resultMap);
        if(resultMap!=null){
            Object boxes = resultMap.get("box");
            Object gender=resultMap.get("gender");
            List<List<Object>> points=(List<List<Object>>)boxes;
            List<String> genders=(List<String>)gender;
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
