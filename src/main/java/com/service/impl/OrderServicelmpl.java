package com.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.User;
import com.service.OrderService;
import com.entity.order;
import com.mapper.orderMapper;
import com.vo.R;

import javax.servlet.http.HttpServletRequest;
import com.vo.param.createParam;
import com.vo.param.searchParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mapper.orderMapper;
import com.service.impl.global;

/**
 * @Auther HYT
 * @Date 2023/5/22
 * @Desc
 */
@Service
//@Transactional
@AllArgsConstructor
public class OrderServicelmpl extends ServiceImpl<orderMapper, order> implements OrderService {

    @Autowired
    private final orderMapper orderMapper;
    @Override
    public R createOrder(createParam createParam,String token) {
       String phone = JWT.decode(token).getAudience().get(0);
        try{
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
        }catch (Exception e){
            return R.error();
        }
    }
    @Override
    public  R searchOrder(searchParam searchParam){

        return R.ok();
    }
}
