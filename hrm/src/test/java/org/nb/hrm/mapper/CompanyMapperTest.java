package org.nb.hrm.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.nb.hrm.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;

    @Test
    public void testAdd() {
        Company company=new Company();
        company.setAddress("湖北武汉");
        company.setName("华为");
        company.setPhone("18407151865");
        company.setRegisterTime(System.currentTimeMillis());
        company.setType(0);
    }


}
