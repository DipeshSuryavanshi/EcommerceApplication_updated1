package com.order.system.service;

import com.order.system.dto.ItemDTO;
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

import static org.mockito.Mockito.when;

public class ItemServiceTest {
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    ItemServiceImpl itemService;
    @Mock
    OrderRepository orederRepository;
    @Mock
    ItemRepository itemRepository;
    @Test
    void testGetAllListOfOrderedItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        List<Item> itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(1L);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        when(itemRepository.findAll()).thenReturn(itemList) ;
        itemService.getAllListOfOrderedItems();

    }
    @Test
    void testGetAllListOfOrderedItemsById() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        List<Item> itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        when(itemRepository.findAll()).thenReturn(itemList) ;
        itemService.getAllListOfOrderedItemsById(id);

    }
    @Test
    void testUpdateExistingOrderItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        Long orderId=1L;
        ItemDTO itemDTO=new ItemDTO();
        Item item=new Item();
        Long itemid=3L;
        Item item1=new Item();
        Set<Item> itemList=new HashSet<>();
        Set<ItemDTO>itemDTOSet=new HashSet<>();
        itemDTO.setItemName("xyz");
        itemDTO.setItemPrice(5.0f);
        itemDTO.setItemId(itemid);
        itemDTO.setItemQuantity(3);
        itemDTO.setShippedDate("2023-98-01");
        itemList.add(item);
        order.setItemList(itemList);
        Mockito.when(orederRepository.findByOrderId(orderId)).thenReturn(order);
        // Set<Long>ids=itemList.stream().map(x->x.getItemId()).collect(Collectors.toSet());
        Mockito.when(itemRepository.findByItemId(itemid)).thenReturn(item);
        itemDTO.setItemId(itemid);
        itemDTO.setItemPrice(item.getItemPrice());
        itemDTO.setItemName(item.getItemName());
        Mockito.when(itemRepository.save(item)).thenReturn(item);
         BeanUtils.copyProperties(itemList,itemDTOSet);

        itemService.updateExistingOrderItem(itemDTO,orderId);

    }
    @Test
    void testUpdateExistingNotinOrder() throws SQLException {
        MockitoAnnotations.initMocks(this);

        Long orderId=1L;
        ItemDTO itemDTO=new ItemDTO();
        Item item=new Item();
      //  Long itemid=3L;
       // Item item1=new Item();
        Set<ItemDTO> itemList=new HashSet<>();
//        itemDTO.setItemName("xyz");
//        itemDTO.setItemPrice(5.0f);
//        itemDTO.setItemId(itemid);
//        itemDTO.setItemQuantity(3);
//        itemDTO.setShippedDate("2023-98-01");
//        itemList.add(itemDTO);
        itemList=null;
        // Mockito.when(itemRepository.save(item)).thenReturn(item);
      // testSaveItem();
        // Mockito.when(orderProcessingService.saveItem(itemList,orderId));
        Mockito.when(itemService.updateExistingOrderItem(itemDTO,orderId)).thenReturn(null);

    }
    @Test
    void testDeleteOrderedItemById() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        List<Item> itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        itemRepository.deleteById(id) ;
        String s1= itemService.deleteOrderedItemById(id);
        Assertions.assertNotNull(s1);

    }
    @Test
    void testSaveItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Order order=new Order();
        order.setCustomerName("xyz");
        order.setCustomerAddress("abc");
        //  order.setOrderDate(LocalDate.parse("20230801"));
        order.setDispatchDate("4566666");
        order.setOrderAmount(666);
        order.setNumberOfItems(23);
        Long id=1L;
        Item item=new Item();
        Set<Item>itemList=new HashSet<>();
        Set<ItemDTO>itemDTOSet=new HashSet<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(1L);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        BeanUtils.copyProperties(itemList,itemDTOSet);
        Mockito.when(orederRepository.findByOrderId(id)).thenReturn(order);
        Mockito.when(itemRepository.save(item)).thenReturn(item) ;
        Assert.assertNotNull(itemService.saveItem(itemDTOSet,id));

    }
}
