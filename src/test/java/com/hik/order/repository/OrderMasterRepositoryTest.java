package com.hik.order.repository;

import com.hik.order.dataobject.OrderMaster;
import com.hik.order.enums.OrderStatusEnum;
import com.hik.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setBuyerAddress("海康");
        orderMaster.setBuyerName("qhl");
        orderMaster.setBuyerOpenid("123");
        orderMaster.setBuyerPhone("187");
        orderMaster.setOrderId("110110");
        orderMaster.setOrderAmount(new BigDecimal(12));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster orderMaster1=orderMasterRepository.save(orderMaster);
        Assert.assertNotEquals(null,orderMaster1);

    }
}