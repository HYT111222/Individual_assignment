package com.vo.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther HYT
 * @Date 2023/5/22
 * @Desc
 */
@Getter
@AllArgsConstructor
public class createParam {
    private String from_user;
    private String from_phone;
    private String from_addr;
    private String to_user;
    private String to_phone;
    private String to_addr;
    private String   carrier;
    private  String cost;
    private  String  weight;
    //待定
}
