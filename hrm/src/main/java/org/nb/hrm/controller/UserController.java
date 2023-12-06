package org.nb.hrm.controller;

import org.nb.hrm.entity.User;
import org.nb.hrm.net.NetCode;
import org.nb.hrm.net.NetResult;
import org.nb.hrm.net.Result;
import org.nb.hrm.net.param.LoginOrRegisterParam;
import org.nb.hrm.service.impl.UserService;
import org.nb.hrm.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
public class UserController {



    private UserService userService;
    private RedisTemplate redisTemplate;

    @Autowired
    public UserController(UserService userService, RedisTemplate redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    //发送验证码
    @GetMapping("/getVerifyCode")
    public NetResult sendVerifyCode(@RequestParam String phone) {
        //检查手机号是否为空
        if (StringUtil.isEmpty(phone)) {
            return ResultGenerator.genErrorResult(NetCode.PHONE_INVALID, "手机号不能为空");
        }
        //检查手机号是否符合手机号的规范
        if (!RegexUtil.isPhoneValid(phone)) {
            return ResultGenerator.genErrorResult(NetCode.PHONE_INVALID, "不是合法的手机号");
        }
        String code ="123456";
        String ssmResult = AliSendSMSUtil.sendSms(code, phone);
        if (ssmResult == null) {
            return ResultGenerator.genFailResult("发送验证码失败");
        }
        //把手机号和验证码存进redis,设置1个小时过期
        redisTemplate.opsForValue().set(phone, code, 60, TimeUnit.MINUTES);
        return ResultGenerator.genSuccessResult(Result.StringToJson(ssmResult));
    }

    //登录和注册
    @PostMapping("/loginOrRegister")
    public NetResult LoginOrRegister(@RequestBody LoginOrRegisterParam loginOrRegisterParam) {
        //获取手机号
        String phone = loginOrRegisterParam.getPhone();

        //检查手机号是否为空
        if (StringUtil.isEmpty(phone)) {
            return ResultGenerator.genErrorResult(NetCode.PHONE_INVALID, "手机号不能为空");
        }

        //检查手机号是否符合手机号的规范
        if (!RegexUtil.isPhoneValid(phone)) {
            return ResultGenerator.genErrorResult(NetCode.PHONE_INVALID, "不是合法的手机号");
        }

        //获取身份
        int role = loginOrRegisterParam.getRole();
        if (role != 0 && role != 1) {
            return ResultGenerator.genErrorResult(NetCode.ROLE_INVALID, "身份错误");
        }
        //查询是否有这个人
        User u = userService.findUser(phone);

        //没有就注册一个
        if (u == null) {
            //从redis读取验证码
            String cachedCode = (String) redisTemplate.opsForValue().get(phone);
            //检查是否有验证码
            if (!StringUtil.isEmpty(cachedCode)) {
                //比对传入的验证码和读取的是否一样
                if (loginOrRegisterParam.getCode().equals(cachedCode)) {
                    u = new User();
                    //copy属性
                    BeanUtils.copyProperties(loginOrRegisterParam, u);
                    userService.register(phone, role);
                    return ResultGenerator.genSuccessResult("注册成功");
                } else {
                    return ResultGenerator.genFailResult("注册失败");
                }
            } else {
                return ResultGenerator.genFailResult("验证码过期");
            }
        }

        //有这个人就登录
        else {
            //从redis读取验证码
            String cachedCode = (String) redisTemplate.opsForValue().get(phone);
            //检查是否有验证码
            if (!StringUtil.isEmpty(cachedCode)) {
                //比对传入的验证码和读取的是否一样
                if (loginOrRegisterParam.getCode().equals(cachedCode)) {
                    //去找这个人
                    User user = userService.login(phone, role);
                    //有这个人
                    if (user != null) {
                        String token = UUID.randomUUID().toString();
                        //给员工设置一个token
                        user.setToken(token);
                        //30分钟token过期
                        redisTemplate.opsForValue().set(token,user, 30, TimeUnit.MINUTES);
                        return ResultGenerator.genSuccessResult(user);
                    }
                    //没找到这个人
                    return ResultGenerator.genFailResult("登录失败");
                }
            } else {
                return ResultGenerator.genFailResult("验证码过期");
            }
        }
        return ResultGenerator.genFailResult("未知的异常");
    }

}

