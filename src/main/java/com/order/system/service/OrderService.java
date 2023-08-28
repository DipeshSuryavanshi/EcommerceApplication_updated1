package com.order.system.service;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.dto.OrderDTO;
import com.order.system.entity.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface OrderService {
    public OrderDTO saveOrderWithItems(OrderDTO order);

    public List<OrderDTO> getOrderWithItems();
    public OrderDTO getOrderWithId(Long id);
    public String updateOrderWithItems(OrderDTO orderDTO, Long orderId);
    public String deleteeOrderWithItems(Long id);


   // Set<Order> getAllListOfOrderByItemId(Long id);
//   public OrderByItem getOrderList(Long itemId);
   // OrderByItem findOrdersByItemId(Long itemId);
    public String updateOrderStatus(Long orderId, String newStatus);
}
