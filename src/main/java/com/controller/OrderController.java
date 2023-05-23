package com.controller;

import com.vo.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.service.OrderService;
import com.vo.param.*;


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

    /**
     * 新建包裹
     * @param createParam
     * @return
     */
    @PostMapping("/create")
    public R login(@RequestBody createParam createParam) {
        String token = request.getHeader("token");
        System.out.println(token);
        return orderService.createOrder(createParam,token);
    }

    /**
     * 查询承运商
     * @param carrier
     * @return
     */
    @GetMapping("/retrieve")
    public R retrieve(@RequestParam(value="carrier") String carrier){
        String token = request.getHeader("token");
        System.out.println(token);
        return orderService.searchOrder(carrier,token);
    }

    /**
     * 查询id
     * @param id
     * @return
     */
    @GetMapping("/retrieveID")
    public R retrieveID(@RequestParam(value="id") String id){
        return orderService.searchParcelID(id);
    }

    /**
     * 查询单个包裹物流
     * @param id
     * @return
     */
    @GetMapping("/retrieveTransportantion")
    public R retrieveTransportantion(@RequestParam(value = "id") String id){
        return orderService.fetchTransportation(id);
    }
}
