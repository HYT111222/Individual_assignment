package com.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.User;
import com.mapper.UserMapper;
import com.service.UserService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mapper.orderMapper;
import java.util.List;


/**
 * FileName:  UserServiceImpl
 * Date: 2023/04/13
 */
@Service
//@Transactional
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    private final UserMapper userMapper;


    // 登陆
    @Override
    public R login(LoginParam loginParam){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",loginParam.getPhone());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null && user.getPassword().equals(loginParam.getPassword())) {
            String token= user.getToken(user);
            return R.ok().data("token",token);
        } else {
           return R.error();
        }
    }

    // 注册
    @Override
    public R register(RegisterParam registerParam) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",registerParam.getPhone());
        boolean u = userMapper.exists(queryWrapper);
        System.out.println(u);
        if(!u) {//用户不存在
            User user=new User();
            user.setPhone(registerParam.getPhone());
            user.setPassword(registerParam.getPassword());
            user.setUsername(registerParam.getUser_name());
            userMapper.insert(user);
            return R.ok();
        }
        else {
            return R.error().message("该手机号已注册");
        }
    }
    //修改密码
//    @Override
//    public R changePassword(ChangeParam changeParam) {
//        R r= new R();
//        // 鉴权，获取username
//        String username = JWT.decode(changeParam.getToken()).getAudience().get(0);
//        System.out.println(username);
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("username",username);
//        User user = userMapper.selectOne(queryWrapper);
//        // 判断该username是否存在
//        if(user!= null && user.getPassword().equals(changeParam.getPre_password())){
//            UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
//            updateWrapper.eq("username",username).set("password",changeParam.getNew_password());
//            userMapper.update(user,updateWrapper);
//            r.data("status_code",true);
//            return r;
//        }
//        else {
//            r.data("status_code",false);
//            return r;
//        }
//    }

    // 主页请求用户信息
//    @Override
//    public R getInformation(String token) {
//        R r = new R();
//        if(token.equals("")){
//            r.data("status_code",false);
//            return r;
//        }
//        // 鉴权，获取username
//        String username = JWT.decode(token).getAudience().get(0);
//        System.out.println(username);
//        // 判断该username是否存在
//        if(username != null){
//            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("username",username);
//            User user = userMapper.selectOne(queryWrapper);
//            String phone = user.getPhone();
//            String address = user.getAddress();
//            String total_cost = user.getTotalcost();
//            r.data("status_code",true);
//            r.data("user_name",username);
//            r.data("phone",phone);
//            r.data("address",address);
//            r.data("total_cost",total_cost);
//        }
//        return r;
//    }
//    //修改个人信息
//    @Override
//    public R changeInformation(ChangeInfoParam changeInfoParam) {
//        R r= new R();
//        // 鉴权，获取username
//        String username = JWT.decode(changeInfoParam.getToken()).getAudience().get(0);
//        System.out.println(username);
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("username",username);
//        User user = userMapper.selectOne(queryWrapper);
//        // 判断该username是否存在
//        if(user!= null ){
//            UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
//            updateWrapper.eq("username",username);
//            if(changeInfoParam.getPhone()!=null)
//            {
//                updateWrapper.set("phone",changeInfoParam.getPhone());
//                System.out.println(changeInfoParam.getPhone());
//           }
//            if ( changeInfoParam.getAddress()!=null) {
//                System.out.println(changeInfoParam.getAddress());
//                updateWrapper.set("address",changeInfoParam.getAddress());
//            }
//            userMapper.update(user,updateWrapper);
//            r.data("status_code",true);
//            return r;
//        }
//        else {
//            r.data("status_code",false);
//            return r;
//        }
//    }
}
