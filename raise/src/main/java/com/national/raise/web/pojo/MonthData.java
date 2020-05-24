package com.national.raise.web.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "r_month_data")
public class MonthData {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    // 商品名称
    private String spmc;

    // 资助编码
    private String zzbm;

    // 家长姓名
    private String jzxm;

    // 宝宝姓名
    private String bbxm;

    // 福利主任
    private String flzr;

    // 所在乡镇
    private String szxz;

    // 所在村
    private String szc;

    // 月份
    private String yf;

}
