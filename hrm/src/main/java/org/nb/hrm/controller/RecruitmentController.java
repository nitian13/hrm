package org.nb.hrm.controller;
import org.nb.hrm.common.Constants;
import org.nb.hrm.entity.Collection;
import org.nb.hrm.entity.Company;
import org.nb.hrm.entity.Recruitment;
import org.nb.hrm.entity.User;
import org.nb.hrm.net.NetCode;
import org.nb.hrm.net.NetResult;
import org.nb.hrm.net.param.RecruitmentParam;
import org.nb.hrm.service.impl.CollectionService;
import org.nb.hrm.service.impl.CompanyService;
import org.nb.hrm.service.impl.RecruitmentService;
import org.nb.hrm.utils.ResultGenerator;
import org.nb.hrm.utils.StringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class RecruitmentController {
    private RecruitmentService recruitmentService;
    private CompanyService companyService;
    private CollectionService collectionService;
    private RedisTemplate redisTemplate;

    public RecruitmentController(RecruitmentService recruitmentService, CompanyService companyService,CollectionService collectionService,RedisTemplate redisTemplate) {
        this.recruitmentService = recruitmentService;
        this.companyService = companyService;
        this.collectionService=collectionService;
        this.redisTemplate=redisTemplate;
    }

    //发布招聘
    @PostMapping("/jobPosting")
    private NetResult JobPosting(@RequestBody RecruitmentParam recruitmentParam) {
        //获取招聘描述
        String description = recruitmentParam.getDescription();
        //检查招聘描述是否为空
        if (StringUtil.isEmpty(description)) {
            return ResultGenerator.genErrorResult(NetCode.DESCRIPTION_INVALID, "招聘描述不能为空");
        }
        //获取工资
        double wage = recruitmentParam.getWage();
        //检查工资
        if (wage <= 0) {
            return ResultGenerator.genErrorResult(NetCode.WAGE_INVALID, "工资小于等于0");
        }
        //获取工作时长
        int workingHour = recruitmentParam.getWorkingHour();
        //检查工作时长
        if (workingHour <= 0 || workingHour >= 12) {
            return ResultGenerator.genErrorResult(NetCode.WORKINGHOUR_INVALID, "工作时长小于0或超额工作");
        }
        //通过id查询招聘信息
        Company company = companyService.findCompanyById(recruitmentParam.getId());
        //获取工作地址
        String address = company.getAddress();
        try {
            recruitmentService.add(wage, workingHour, description, address);
            return ResultGenerator.genSuccessResult("发布招聘成功");
        } catch (Exception e) {
            return ResultGenerator.genFailResult("未知的异常" + e.getMessage());
        }

    }

    //通过工资去查询招聘职位
    @GetMapping("/recruitmentlist")
    public NetResult jobList(@RequestParam double wage) {
        //工资检查
        if (wage <= 0) {
            return ResultGenerator.genErrorResult(NetCode.MONEY_INVALID, "工资小于0");
        }
        try {
            //获取列表
            List<Recruitment> recruitmentList=recruitmentService.findJobByMoney(wage);
            //判断null
            if (recruitmentList==null){
                return ResultGenerator.genFailResult("没有找到相关信息");
            }
            return ResultGenerator.genSuccessResult(recruitmentList);
        } catch (Exception e) {
            return ResultGenerator.genFailResult("未知的异常" + e.getMessage());
        }
    }

    //查询招聘详情
    @GetMapping("/particulars")
    public NetResult  particulars(@RequestParam Long id) {
        //检查数据
        if (id <= 0) {
            return ResultGenerator.genErrorResult(NetCode.MONEY_INVALID, "id错误");
        }
        //通过id获取招聘信息
        Recruitment recruitment=recruitmentService.findById(id);
        //判断null
        if (recruitment==null){
            return ResultGenerator.genFailResult("没有找到相关信息");
        }
        return ResultGenerator.genSuccessResult(recruitment);
    }

    //收藏
    @PostMapping("/collection")
    public NetResult  collection(@RequestParam Long id, HttpServletRequest request) {
        //从请求头拿到token
        String token = request.getHeader("token");
        //判断token是否存在
        if (StringUtil.isEmpty(token)) {
            return ResultGenerator.genErrorResult(NetCode.TOKEN_NOT_EXIST, "token不存在");
        }
        User user = (User) redisTemplate.opsForValue().get(token);
        //判断user的token是否过期
        if (user == null) {
            return ResultGenerator.genErrorResult(NetCode.TOKEN_INVALID, Constants.INVALID_TOKEN);
        }
        long userId=user.getId();
        //检查数据
        if (id <= 0) {
            return ResultGenerator.genErrorResult(NetCode.MONEY_INVALID, "id错误");
        }
        //通过id获取招聘信息
        Recruitment recruitment=recruitmentService.findById(id);
        //判断null
        if (recruitment==null){
            return ResultGenerator.genFailResult("没有找到相关信息");
        }
        Collection collection=new Collection();
        collection.setUserId(userId);
        collection.setRecruitmentId(id);
        collection.setTime(System.currentTimeMillis());
        int count=collectionService.add(collection);
        if (count!=1){
            return ResultGenerator.genFailResult("收藏失败");
        }
        return ResultGenerator.genSuccessResult(collection);
    }


}
