package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**保存的发货人信息
 * @Auther HYT
 * @Date 2023/5/23
 * @Desc
 */
@Data
@TableName("addrfrom")
public class addrFrom {
    private String phone;
    private String fromname;
    private String fromphone;
    private String fromadrr;
}
