package com.order.system.service;


import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.dto.OrderDTO;
import com.order.system.dto.Status;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.repository.ItemRepository;
import com.order.system.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderProcessingServiceTest {

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    OrderServiceImpl orderProcessingService;
    @Mock
    OrderRepository orederRepository;
    @Mock
    ItemRepository itemRepository;

    @Test
    void testSaveOrderWithItems(){
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        OrderDTO orderDTO=new OrderDTO();
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        BeanUtils.copyProperties(order,orderDTO);
        //order.setItemList(List.of());
        when(orederRepository.save(order)).thenReturn(order) ;
        orderProcessingService.saveOrderWithItems(orderDTO);
    }
    @Test
    void testGetOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        OrderDTO orderDTO=new OrderDTO();
        List<Order>orderList=new ArrayList<>();
        List<OrderDTO>orderDTOList=new ArrayList<>();
        order.setOrderId(Long.valueOf("01"));
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        // order.setItemList(List.of());
        orderList.add(order);
        BeanUtils.copyProperties(orderList,orderDTOList);
        when(orederRepository.findAll()).thenReturn(orderList) ;
        orderProcessingService.getOrderWithItems();

    }
    @Test
    void testGetOrderWithId() throws SQLException {
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
        //  order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.findByOrderId( id)).thenReturn(order);
        orderProcessingService.getOrderWithId(id);

    }
    @Test
    void testUpdateOrderWithItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        OrderDTO orderDTO=new OrderDTO();
        List<Order>orderList=new ArrayList<>();
        Long orderId=1L;
        Long itemId=2L;
        orderDTO.setOrderId(orderId);
        orderDTO.setCustomerName("xyz");
        orderDTO.setCustomerAddress("abc");
        // order.setOrderDate(LocalDate)"123445");
        orderDTO.setDispatchDate("4566666");
        orderDTO.setOrderAmount(666);
        orderDTO.setNumberOfItems(23);
        // order.setItemList(List.of());
        orderList.add(order);
        when(orederRepository.findByOrderId(orderId)).thenReturn(order);
        when(orederRepository.save(order)).thenReturn(order);
        orderProcessingService.updateOrderWithItems(orderDTO,orderId);
        // Assert.assertNotNull(order);

    }
    @Test
    void testUpdateOrderWithItemsNull() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        OrderDTO orderDTO=new OrderDTO();
        order=null;
        List<Order>orderList=new ArrayList<>();
        Long orderId=1L;
        Long itemId=2L;
         orderDTO.setOrderId(orderId);
        orderList.add(order);
        when(orederRepository.save( order)).thenReturn(null);
        orderProcessingService.updateOrderWithItems(orderDTO,orderId);

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
        //  order.setItemList(List.of());
        orderList.add(order);
        orederRepository.deleteById(id);
        orderProcessingService.deleteeOrderWithItems(id);

    }

//    @Test
//    void testGetOrderList() throws SQLException {
//        MockitoAnnotations.initMocks(this);
//        OrderByItem orderByItem =new OrderByItem();
//        Long id;
//        id=1L;
//       List<Long> ids=List.of(1L,2L,3L);
//        orderByItem.setItemId(id);
//        orderByItem.setMsg("add");
//        orderByItem.setOrderIdList(ids);
//        when(orederRepository.findOrdersByItemId(id)).thenReturn(ids) ;
//        orderProcessingService.getOrderWithId(id);
//
//    }
    @Test
    void testUpdateOrderStatus() {
        Long orderId = 123L;
        String newStatus = "shipped";

        Order mockOrder = new Order();
      //  mockOrder.setOrderDate(LocalDate.parse("23-4-11"));
        mockOrder.setOrderId(orderId);
        mockOrder.setOrderAmount(23.2f);
        mockOrder.setCustomerName("name");
        mockOrder.setStatus(Status.PENDING);

      Mockito.when(orederRepository.findByOrderId(orderId)).thenReturn(mockOrder);
        mockOrder.setStatus(Status.PROCESSED);
        Mockito.when(orederRepository.save(mockOrder)).thenReturn(mockOrder);
        orderProcessingService.updateOrderStatus(orderId,Status.PROCESSED.name());
    }
    @Test
    void testUpdateOrderStatusNull() {
        Long orderId=null;
        Order mockOrder = new Order();
        //  mockOrder.setOrderDate(LocalDate.parse("23-4-11"));


        Mockito.when(orederRepository.findByOrderId(orderId)).thenReturn(null);
        mockOrder.setStatus(Status.PROCESSED);
        Mockito.when(orederRepository.save(mockOrder)).thenReturn(null);
        orderProcessingService.updateOrderStatus(orderId,Status.PROCESSED.name());
    }



}

