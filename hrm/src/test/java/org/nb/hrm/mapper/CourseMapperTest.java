package org.nb.hrm.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nb.hrm.entity.Company;
import org.nb.hrm.net.param.CourseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMapperTest {
    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void testAdd() {
        CourseParam courseParam = new CourseParam();
        courseParam.setName("java课");
        courseParam.setContent("java就业");
        courseParam.setTeacher("ikun");
        courseParam.setDuration("250h");
        courseMapper.add(courseParam);
    }
}
