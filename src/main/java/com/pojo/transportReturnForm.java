package com.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther HYT
 * @Date 2023/5/23
 * @Desc
 */
@Setter
@Getter
public class transportReturnForm {
    private String siteName;
    private String receiveTime;
    private String offTime;
    private String siteAttribute;//站点属于起点终点还是中转站
}
