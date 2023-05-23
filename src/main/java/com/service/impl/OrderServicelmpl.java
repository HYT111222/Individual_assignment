package com.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.User;
import com.service.OrderService;
import com.entity.*;
import com.mapper.*;
import com.vo.R;
import com.pojo.*;

import javax.servlet.http.HttpServletRequest;
import com.vo.param.createParam;
import com.vo.param.searchParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mapper.orderMapper;
import com.service.impl.global;

import java.util.List;

/**
 * @Auther HYT
 * @Date 2023/5/22
 * @Desc
 */
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDc5Mzg5ODYzOSJ9.5uqrYIJwAU0QxsG-hJlpg5FSprLxoQNxA68FDecE7-w
@Service
//@Transactional
@AllArgsConstructor
public class OrderServicelmpl extends ServiceImpl<orderMapper, order> implements OrderService {

    @Autowired
    private final orderMapper orderMapper;
    @Autowired
    private final transportationMapper transportationMapper;
    @Override
    public R createOrder(createParam createParam,String token) {
       String phone = JWT.decode(token).getAudience().get(0);
        //try{
            order order = new order();
            order.setFromname(createParam.getFrom_user());
            order.setFromphone(createParam.getFrom_phone());
            order.setFromaddr(createParam.getFrom_addr());
            order.setToname(createParam.getTo_user());
            order.setTophone(createParam.getTo_phone());
            order.setToaddr(createParam.getTo_addr());
            order.setSttatus("待发货");
            String time=global.time();
            order.setSteplist("待揽收"+time);
            order.setCostnum(createParam.getCost());
            order.setCarrpeople(createParam.getCarrier());
            order.setWeightnum(createParam.getWeight());
            order.setCreatetime(time);
            order.setPhone(phone);
            orderMapper.insert(order);
            QueryWrapper<order> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("phone",phone).eq("createtime",time);
            order orderTemp=orderMapper.selectOne(queryWrapper);
            return R.ok().data("shippment_id",orderTemp.getId());
//        }catch (Exception e){
//            return R.error();
//        }
    }
    //根据承运商查
    @Override
    public  R searchOrder(String carrier,String token){
        String phone = JWT.decode(token).getAudience().get(0);
        QueryWrapper<order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("carrpeople",carrier).eq("phone",phone);
        try{
            List<order> listOrder=orderMapper.selectList(queryWrapper);

                for(int i=0;i<listOrder.size();i++){
                    searchReturnForm result=new searchReturnForm();
                    result.setFrom_user(listOrder.get(i).getFromname());
                    result.setFrom_phone(listOrder.get(i).getFromphone());
                    result.setFrom_addr(listOrder.get(i).getFromaddr());
                    result.setTo_user(listOrder.get(i).getToname());
                    result.setTo_phone(listOrder.get(i).getTophone());
                    result.setTo_addr(listOrder.get(i).getToaddr());
                    result.setTransportation_state(listOrder.get(i).getSttatus());
                    QueryWrapper<transportation> transportationQueryWrapper = new QueryWrapper<>();
                    transportationQueryWrapper.eq("id",listOrder.get(i).getId());
                    List<transportation> tampTransportation=transportationMapper.selectList(transportationQueryWrapper);
                    result.setTransportation(tampTransportation);
                    return R.ok().data("searchCarrierList",result);
                }

                return R.ok().data("searchCarrierList",null);

        }catch (Exception e){
            return R.error().message("发生异常："+e.toString());
        }
    }

    //根据id查包裹
    @Override
    public R searchParcelID(String id) {
        QueryWrapper<order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        try{
            if(orderMapper.exists(queryWrapper)){
                order order=orderMapper.selectOne(queryWrapper);
                searchReturnForm result=new searchReturnForm();
                result.setFrom_user(order.getFromname());
                result.setFrom_phone(order.getFromphone());
                result.setFrom_addr(order.getFromaddr());
                result.setTo_user(order.getToname());
                result.setTo_phone(order.getTophone());
                result.setTo_addr(order.getToaddr());
                result.setTransportation_state(order.getSttatus());
                QueryWrapper<transportation> transportationQueryWrapper = new QueryWrapper<>();
                transportationQueryWrapper.eq("id",id);
                List<transportation> tampTransportation=transportationMapper.selectList(transportationQueryWrapper);
                result.setTransportation(tampTransportation);
                return R.ok().data("searchCarrierList",result);
            }else {
                return R.ok().message("该包裹不存在");
            }
        }catch (Exception e){
            return R.error().message("发生异常："+e.toString());
        }

    }
    //获取单个包裹物流
    @Override
    public R fetchTransportation(String id) {
        QueryWrapper<transportation> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        QueryWrapper<order> queryWrapperOrder=new QueryWrapper<>();
        queryWrapperOrder.eq("id",id);
        try {
            if (orderMapper.exists(queryWrapperOrder)) {
                List<transportation> tampTransportation=transportationMapper.selectList(queryWrapper);
                return R.ok().data("transprtation",tampTransportation);
            }else {
                return R.ok().message("该包裹不存在");
            }
        }catch (Exception e){
            return R.error().message(e.toString());
        }
    }
}
