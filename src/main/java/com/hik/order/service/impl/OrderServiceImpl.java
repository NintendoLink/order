package com.hik.order.service.impl;

import com.hik.order.DTO.CartDTO;
import com.hik.order.DTO.OrderDTO;
import com.hik.order.client.ProductClient;
import com.hik.order.dataobject.OrderDetail;
import com.hik.order.dataobject.OrderMaster;
import com.hik.order.dataobject.ProductInfo;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        /**
         * 每一次执行产生一个OrderID
         */
        String orderId=Keys.getUniqueKey();
        /**
         * TODO 查询商品信息(调用商品服务)
         * TODO 3.计算总价
         * TODO 4.扣库存(调用商品服务)
         * TODO 5.订单入库
         */
        /**
         * 查询商品信息，调用productClient
         */
        List<String> productIdList=orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo>productInfoList=productClient.listForOrder(productIdList);
        for(ProductInfo productInfo:productInfoList){
            System.out.println(productInfo.getProductId());
        }
        /**
         * 计算总价
         */
        BigDecimal orderAmount=new BigDecimal(0);
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getProductId().equals(orderDetail.getProductId())){
                    /**
                     * 计算出一件商品的总价后，累加，然后写入OrderMaster中
                     */
                    orderAmount=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(Keys.getUniqueKey());
                    /**
                     * 订单详情入库
                     */
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        /**
         * 扣库存
         */
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        /**
         * 订单主单入库
         */
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
