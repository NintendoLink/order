package com.hik.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 检测配置是否拿到
 */
@RestController
@RequestMapping(value = "/env")
public class ConfigCheckController {
//    @Value("${env}")
//    private String env;

    @GetMapping("/print")
    public String print(){
        return "env";
    }
}
