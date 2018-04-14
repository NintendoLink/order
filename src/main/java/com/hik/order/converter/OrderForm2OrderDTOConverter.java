package com.hik.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hik.order.DTO.OrderDTO;
import com.hik.order.dataobject.OrderDetail;
import com.hik.order.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTOConverter {


    public static OrderDTO converter(OrderForm orderForm)  {
        Gson gson=new Gson();
        OrderDTO orderDTO=new OrderDTO();

        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        try {
            orderDetailList=gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            System.out.println("对象转换出错");
//            throw new SellException(ResultEnum.PARAM_ERROR);
        }


        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
