package com.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("shipment")
public class transportation{

    private int id; // 主键

    private String intime;

    private String outtime;

    private String sitename;//主键
}
