package com.order.system.repository;

import com.order.system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    public Order findByOrderId(Long id);
    @Query("SELECT   o.orderId  FROM Order o JOIN  o.itemList i WHERE i.itemId = :itemId")
    List<Long> findOrdersByItemId(Long itemId);

//    @Query("select o FROM Order o")
//    List<Order> getOrderList();

}

