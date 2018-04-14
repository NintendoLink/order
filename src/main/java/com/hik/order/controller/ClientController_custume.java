package com.hik.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController_custume {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/getproductmsg")
    public String getProductMsg(){
        /**
         * 1.使用RestTmplate第一种调用方式
         * 缺点 地址写死 对方有可能有多个实例 多个地址
         */
//        RestTemplate restTemplate=new RestTemplate();
//        String reponse=restTemplate.getForObject("http://localhost:8080/msg",String.class);
//        System.out.println(reponse);
//        return reponse;

        /**
         * 2.第二种方式
         * 利用LoadBalancerClient.choose(应用名)来获取应用地址和端口
         */
        ServiceInstance serviceInstance=loadBalancerClient.choose("PRODUCT");
        serviceInstance.getHost();
//        动态的拿到地址
        String url=String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"/msg";
        System.out.println(url);
        RestTemplate restTemplate=new RestTemplate();
        String reponse=restTemplate.getForObject(url,String.class);
        System.out.println(reponse);
        return reponse;

        /**
         * 3.第三种方式，利用config中的bean注解
         * 利用@LoadBalanced，可以在restTEmplate使用应用名字
         */
//        String response=restTemplate.getForObject("http://PRODUCT/msg",String.class);
//        return response;
    }
}
