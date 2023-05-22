package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * @Auther HYT
 * @Date 2023/5/22
 * @Desc
 */
@Data
@TableName("orderlist")
public class order {
    @TableId(type = IdType.ASSIGN_ID)
    private int id;
    private String fromname;
    private String fromphone;
    private String fromaddr;
    private String toname;
    private String tophone;
    private String toaddr;
    private String sttatus;
    private String steplist;
    private String carrpeople;
    private String costnum;
    private String weightnum;
    //创建的用户，创建时间
    private String createtime;
    private String phone;

}
