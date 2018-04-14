package com.hik.order.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductVO {
    @JsonProperty(value = "name")
    private String cateGory;

    @JsonProperty(value = "type")
    private Integer type;

    @JsonProperty(value = "foods")
    private List<ProductInfoVO> productInfoVOList;


    public ProductVO() {
    }

    public String getCateGory() {
        return cateGory;
    }

    public void setCateGory(String cateGory) {
        this.cateGory = cateGory;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<ProductInfoVO> getProductInfoVOList() {
        return productInfoVOList;
    }

    public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList) {
        this.productInfoVOList = productInfoVOList;
    }
}
