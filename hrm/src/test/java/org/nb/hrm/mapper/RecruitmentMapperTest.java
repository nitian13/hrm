package org.nb.hrm.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nb.hrm.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RecruitmentMapperTest {
    @Autowired
    private RecruitmentMapper recruitmentMapper;

    @Test
    public void testAdd() {
        recruitmentMapper.add(250,1,"1","1");
    }
}
