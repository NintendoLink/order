package com.hik.order.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductInfoVO {
    @JsonProperty(value = "id")
    private String productId;

    @JsonProperty(value = "name")
    private String productName;

    @JsonProperty(value = "price")
    private BigDecimal productPrice;

    @JsonProperty(value = "description")
    private String productDescription;

    @JsonProperty(value = "icon")
    private String productIcon;
}
