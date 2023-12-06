package org.nb.hrm.controller;

import org.nb.hrm.net.NetCode;
import org.nb.hrm.net.NetResult;
import org.nb.hrm.net.param.CourseParam;
import org.nb.hrm.service.impl.CourseService;
import org.nb.hrm.utils.ResultGenerator;
import org.nb.hrm.utils.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //发布课程
    @PostMapping("/publishCourse")
    private NetResult PublishCourse(@RequestBody CourseParam courseParam) {
        String name=courseParam.getName();
        //检查课程名
        if (StringUtil.isEmpty(name)) {
            return ResultGenerator.genErrorResult(NetCode.NAME_INVALID, "课程名不能为空");
        }

        //获取内容
        String content = courseParam.getContent();

        //检查内容是否为空
        if (StringUtil.isEmpty(content)) {
            return ResultGenerator.genErrorResult(NetCode.CONTENT_INVALID, "课程内容不能为空");
        }
        //获取课程老师
        String teacher=courseParam.getTeacher();

        //检查课程老师
        if (StringUtil.isEmpty(teacher)) {
            return ResultGenerator.genErrorResult(NetCode.TEACHER_INVALID, "课程老师不能为空");
        }
        try {
            courseService.add(courseParam);
            return ResultGenerator.genSuccessResult("发布课程成功");
        } catch (Exception e) {
            return ResultGenerator.genFailResult("未知的异常" + e.getMessage());
        }

    }
}
