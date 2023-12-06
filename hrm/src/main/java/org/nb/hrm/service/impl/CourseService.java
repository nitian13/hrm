package org.nb.hrm.service.impl;

import org.nb.hrm.mapper.CourseMapper;
import org.nb.hrm.net.param.CourseParam;
import org.nb.hrm.service.ICourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CourseService implements ICourseService {
    private CourseMapper courseMapper;

    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public int add(CourseParam courseParam) {
        return courseMapper.add(courseParam);
    }
}
