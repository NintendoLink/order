package com.hik.order.service.impl;

import com.hik.order.DTO.OrderDTO;
import com.hik.order.dataobject.OrderMaster;
import com.hik.order.enums.OrderStatusEnum;
import com.hik.order.enums.PayStatusEnum;
import com.hik.order.repository.OrderDetailRepository;
import com.hik.order.repository.OrderMasterRepository;
import com.hik.order.service.OrderService;
import com.hik.order.utils.Keys;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        /**
         * TODO 查询商品信息(调用商品服务)
         * TODO 3.计算总价
         * TODO 4.扣库存(调用商品服务)
         * TODO 5.订单入库
         */
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(Keys.getUniqueKey());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(new BigDecimal(100));
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
