package org.nb.hrm.controller;

import org.nb.hrm.common.Constants;
import org.nb.hrm.entity.User;
import org.nb.hrm.net.NetCode;
import org.nb.hrm.net.NetResult;
import org.nb.hrm.net.param.CompanyParam;
import org.nb.hrm.service.impl.CompanyService;
import org.nb.hrm.utils.ResultGenerator;
import org.nb.hrm.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CompanyController {
    private CompanyService companyService;
    private RedisTemplate redisTemplate;



    @Autowired
    public CompanyController(CompanyService companyService,RedisTemplate redisTemplate) {
        this.companyService = companyService;
        this.redisTemplate=redisTemplate;
    }

    //绑定公司或机构
    @PostMapping("/bind")
    private NetResult Bind(HttpServletRequest request,@RequestBody CompanyParam companyParam) {
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
        //获取地址
         String address=companyParam.getAddress();

        //检查地址是否为空
        if (StringUtil.isEmpty(address)) {
            return ResultGenerator.genErrorResult(NetCode.ADDRESS_INVALID, "地址不能为空");
        }
        //获取名字
        String name=companyParam.getName();

        //检查名字是否为空
        if (StringUtil.isEmpty(name)) {
            return ResultGenerator.genErrorResult(NetCode.NAME_INVALID, "名字不能为空");
        }
        //获取身份
        int type=companyParam.getType();
        //检查身份
        if(type!=0&&type!=1){
            return ResultGenerator.genErrorResult(NetCode.TYPE_INVALID, "绑定身份错误");
        }
        try {
            //注册时间
            Long registerTime = System.currentTimeMillis();
            //手机号
            String phone=user.getPhone();
            //绑定信息，添加信息
            companyService.register(name,address,registerTime,phone,type);
            return ResultGenerator.genSuccessResult("绑定成功");
        } catch (Exception e) {
            return ResultGenerator.genFailResult("未知的异常" + e.getMessage());
        }

    }

}
