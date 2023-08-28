package com.order.system.controller;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.dto.OrderDTO;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.service.ItemServiceImpl;
import com.order.system.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@RunWith(MockitoJUnitRunner.class)
public class OrderProcessingControllerTest {
    @InjectMocks
    OrderController orderProcessingController;
    @Mock
    OrderServiceImpl orderProcessingService;
    @Mock
    ItemServiceImpl itemService;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);


    }
@Test
    void testAddOrderWithItems() throws SQLException {
    MockitoAnnotations.initMocks(this);
    OrderDTO order=new OrderDTO();
    order.setOrderId(Long.valueOf("01"));
    order.setCustomerName("xyz");
    order.setCustomerAddress("abc");
  //  order.setOrderDate(LocalDate.parse("20230801"));
    order.setDispatchDate("4566666");
    order.setOrderAmount(666);
    order.setNumberOfItems(23);
    //order.setItemList(List.of());
   Mockito.when(orderProcessingService.saveOrderWithItems(order)).thenReturn(order) ;
   orderProcessingController.addOrderWithItems(order);

}
@Test
void testGetOrderWithItems() throws SQLException {
    MockitoAnnotations.initMocks(this);
    OrderDTO order=new OrderDTO();
    List<OrderDTO>orderList=new ArrayList<>();
    order.setOrderId(Long.valueOf("01"));
    order.setCustomerName("xyz");
    order.setCustomerAddress("abc");
    //  order.setOrderDate(LocalDate.parse("20230801"));
    order.setDispatchDate("4566666");
    order.setOrderAmount(666);
    order.setNumberOfItems(23);
    //order.setItemList(List.of());
    orderList.add(order);
    Mockito.when(orderProcessingService.getOrderWithItems()).thenReturn(orderList) ;
    orderProcessingController.getOrderWithItems();

}
    @Test
    void testGetOrderWithId() throws SQLException {
        MockitoAnnotations.initMocks(this);
        OrderDTO order=new OrderDTO();

        List<OrderDTO>orderList=new ArrayList<>();
        Long id;
        id=1L;
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        //order.setItemList(List.of());
        orderList.add(order);
        Mockito.when(orderProcessingService.getOrderWithId( id)).thenReturn(order);
        orderProcessingController.getOrderWithId(id);

    }
    @Test
    void testUpdateOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        OrderDTO order=new OrderDTO();

        List<OrderDTO>orderList=new ArrayList<>();
        Long orderId;
        Long itemId=2L;
        orderId=1L;
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        //order.setItemList(List.of());
        orderList.add(order);
        Mockito.when(orderProcessingService.updateOrderWithItems(order,orderId )).thenReturn("Updated");
        orderProcessingController.updateOrderWithItems(order,orderId );

    }
    @Test
    void testDeleteeOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();

        List<Order>orderList=new ArrayList<>();
        Long id;
        id=1L;
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        //order.setItemList(List.of());
        orderList.add(order);
        Mockito.when(orderProcessingService.deleteeOrderWithItems( id)).thenReturn("deleted");
        orderProcessingController.deleteeOrderWithItems(id);

    }

    @Test
    void testUpdateOrderStatus() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        String status="UPDATED";

        Mockito.when(orderProcessingService.updateOrderStatus(id,status)).thenReturn("UPDATED");
        orderProcessingController.updateOrderStatus(id,"itemList");

    }
}
