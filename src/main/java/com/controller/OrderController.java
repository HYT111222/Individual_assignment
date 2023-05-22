package com.controller;

import com.vo.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.service.OrderService;
import com.vo.param.createParam;
import com.service.UserService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;

/**
 * @Auther HYT
 * @Date 2023/5/22
 * @Desc
 */
@RestController
@RequestMapping("/shippingOrder")
//@CrossOrigin
@AllArgsConstructor
public class OrderController {
    @Resource
    private  OrderService orderService ;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/create")
    public R login(@RequestBody createParam createParam) {
        String token = request.getHeader("token");
        System.out.println(token);
        return orderService.createOrder(createParam,token);
    }
}
