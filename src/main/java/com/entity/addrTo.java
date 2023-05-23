package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Auther HYT
 * @Date 2023/5/23
 * @Desc
 */
@Data
@TableName("addrto")
public class addrTo {
    private String phone;
    private String toname;
    private String tophone;
    private String toadrr;
}
