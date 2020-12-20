package com.shin.controller;

import com.shin.pojo.User;
import com.shin.service.ApplyService;
import com.shin.service.InvokingCountService;
import com.shin.utils.AIInterface;
import com.shin.utils.ImageTran;
import com.shin.utils.Result;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    ApplyService applyService;

    @RequestMapping("/test")
    public Result test(@RequestBody Map<String,String> map) throws IOException {
        String base64 = map.get("base64Data");
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> request=new HashMap<>();
        request.put("baseimg",base64);
        request.put("people_id","123");
        Map<String, Object> resultMap =restTemplate.postForObject("http://lggsoft.vicp.net:8000"+"/face/recognize", request, Map.class);
        System.out.println(resultMap);
        if(resultMap!=null){
            List<List<Object>> boxes=(List<List<Object>>)resultMap.get("boxes");
            BufferedImage image = ImageTran.base64ToBufferedImage(base64);
            if(boxes.isEmpty()){
                return Result.error(ImageTran.BufferedImageToBase64(image));
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

            return Result.success(ImageTran.BufferedImageToBase64(image));
        }
        return Result.error("未检测到人脸");
    }
    
    /**
     * 人脸识别
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/face_recognize")
    public Result faceDetection(MultipartFile file) throws IOException {
        return applyService.faceDetection(file);
    }

    /**
     * 年龄检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/age_estimation")
    public Result ageEstimation(MultipartFile file) throws IOException {
        return applyService.ageEstimation(file);
    }

    /**
     * 目标检测
     * @param file 图片
     * @return Result
     */
    @RequestMapping("/object_detection")
    public Result objectDetection(MultipartFile file) throws IOException {
        return applyService.objectDetection(file);
    }

    /**
     * 烟雾检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/smoke_detection")
    public Result smokeDetection(MultipartFile file) throws IOException {
        return applyService.smokeDetection(file);
    }

    /**
     * 驾驶员状态检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/distractedDriverDetection")
    public Result distractedDriverDetection(MultipartFile file) throws IOException {
        return applyService.distractedDriverDetection(file);
    }

    /**
     * 口罩检测
     * @param file 图片
     * @return Result
     * @throws IOException
     */
    @RequestMapping("/mask_detection")
    public Result maskDetection(MultipartFile file) throws IOException {
        return applyService.maskDetection(file);
    }

    /**
     * 性别检测
     * @param file 图片
     * @return result
     */
    @RequestMapping("/gender_detection")
    public Result genderDetection(MultipartFile file) throws IOException {
        return applyService.genderDetection(file);
    }
}
