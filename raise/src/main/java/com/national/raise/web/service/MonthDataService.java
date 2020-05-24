package com.national.raise.web.service;

import com.national.raise.web.mapper.MonthDataMapper;
import com.national.raise.web.pojo.MonthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MonthDataService {

    @Autowired
    private MonthDataMapper monthDataMapper;

    public MonthData queryById(Long id) {
        return monthDataMapper.selectByPrimaryKey(id);
    }

    //    @Transactional
    public void saveMonthData(MonthData monthData) {

        List<MonthData> existList = getExistList(monthData);
        System.out.println(existList);

        // 当每月存在宝宝姓名，不入库
        if (!existList.isEmpty()) {
            return;
        }
        monthDataMapper.insertSelective(monthData);
    }

    /**
     * 根据宝宝姓名和月份查询数据
     */
    private List<MonthData> getExistList(MonthData monthData) {
        Example example = new Example(MonthData.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("bbxm", monthData.getBbxm());
        criteria.andEqualTo("yf", monthData.getYf());

        return monthDataMapper.selectByExample(example);
    }
}
