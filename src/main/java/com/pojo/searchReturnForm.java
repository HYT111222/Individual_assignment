package com.pojo;

import lombok.Getter;
import lombok.Setter;
import com.entity.*;
import com.pojo.*;

import java.util.List;

/**
 * @Auther HYT
 * @Date 2023/5/23
 * @Desc
 */
@Setter
@Getter
public class searchReturnForm {
    /**
     * {
     *     "from_user": "string",
     *     "from_phone": "string",
     *     "from_addr": "string",
     *     "to_user": "string",
     *     "to_phone": "string",
     *     "to_addr": "string",
     *     "transportation_state": "string",
     *     "transportation": [
     *         "string"
     *     ],
     *     "status": true
     */
    private String from_user;
    private String from_phone;
    private String from_addr;
    private String to_user;
    private String to_phone;
    private String to_addr;
    private String transportation_state;
    private List<transportation> transportation;
}
