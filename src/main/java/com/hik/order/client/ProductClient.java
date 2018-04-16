package com.hik.order.client;

import com.hik.order.DTO.CartDTO;
import com.hik.order.dataobject.ProductInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping(value = "/msg")
    String productMsg();

    /**
     * @RequestBody必须使用PostMapping
     * @param productId
     * @return
     */
    @PostMapping(value = "/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productId);

    @PostMapping(value = "/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
}
