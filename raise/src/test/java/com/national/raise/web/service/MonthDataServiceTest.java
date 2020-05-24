package com.national.raise.web.service;

import com.national.raise.web.pojo.MonthData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static com.national.raise.web.utils.ExcelUtils.readExcel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonthDataServiceTest {

    @Autowired
    private MonthDataService monthDataService;

    @Test
    public void queryById() {
        MonthData monthData = monthDataService.queryById(1L);
        System.out.println("monthData = " + monthData);
    }

    @Test
    public void saveUser() {

        MonthData monthData = new MonthData();
        monthData.setSpmc("国奶扶贫差额资助(广西玉林博白）");
        monthData.setZzbm("2107552278");
        monthData.setJzxm("庞柳");
        monthData.setBbxm("庞英琪");
        monthData.setFlzr("黄倩");
        monthData.setSzxz("水鸣");
        monthData.setSzc("联合");
        monthData.setYf("3");
        monthDataService.saveMonthData(monthData);
    }

    @Test
    public void monthData() throws Exception {
        File file = new File("D:\\code2\\raise\\raise\\src\\test\\sources\\excel\\10.xlsx");
        List<MonthData> datas = readExcel(file);
        for (MonthData data : datas) {
            monthDataService.saveMonthData(data);
        }
    }


}
