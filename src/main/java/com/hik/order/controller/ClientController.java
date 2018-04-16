package com.hik.order.controller;

import com.hik.order.DTO.CartDTO;
import com.hik.order.client.ProductClient;
import com.hik.order.dataobject.ProductInfo;
import com.hik.order.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping(value = "/msg")
    public String getProductMsg(){
        String response=productClient.productMsg();
        return response;
    }

    @GetMapping(value = "/getProductList")
    public String getProductList(){
        List<ProductInfo> productInfoList=productClient.listForOrder(Arrays.asList("2"));
        System.out.println(productInfoList.toString());
        return "OK";
    }

    @GetMapping(value = "/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("2",10)));
        return "ok";
    }

}
