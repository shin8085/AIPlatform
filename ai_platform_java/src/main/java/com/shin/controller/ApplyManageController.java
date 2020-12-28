package com.shin.controller;

import com.shin.pojo.Apply;
import com.shin.service.ApplyManageService;
import com.shin.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/applyManage")
public class ApplyManageController {

    @Resource
    ApplyManageService applyManageService;

    @RequestMapping("/getApplyInfo")
    public Result getApplyInfo(){
        List<Apply> applyInfo = applyManageService.getApplyInfo();
        return Result.success("success",applyInfo);
    }

    @RequestMapping("/updateApplyInfo")
    public Result updateApplyInfo(@RequestBody Apply apply){
        System.out.println(apply);
        int r = applyManageService.updateApplyInfo(apply);
        if(r==0){
            return Result.error();
        }else{
            return Result.success();
        }
    }
}
