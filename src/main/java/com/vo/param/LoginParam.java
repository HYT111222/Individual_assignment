package com.vo.param;
import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * FileName: LoginParam
 * Date: 2023/04/02
 * Description: 该类用于封装 登陆 请求提交的数据
 */
@Getter
@AllArgsConstructor
public class LoginParam {
    private String phone; //用户名
    private String password; //密码

}
