package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.order;
import com.vo.R;
import com.vo.param.*;


public interface OrderService extends IService<order> {
    R createOrder(createParam createParam,String token);

    R searchOrder(String carrier,String token);

    R searchParcelID(String id);

    R fetchTransportation(String id);
}
