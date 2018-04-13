package com.hik.order.repository;

import com.hik.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

}
