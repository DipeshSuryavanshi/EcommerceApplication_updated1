package com.order.system.controller;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.entity.Item;
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

public class ItemControllerTest {
    @InjectMocks
    ItemController itemController;
    @Mock
    OrderServiceImpl orderProcessingService;
    @Mock
    ItemServiceImpl itemService;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);


    }
    @Test
    void testGetAllListOfOrderedItems() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        List<Item> itemList=new ArrayList<>();
        List<ItemDTO> itemDTOList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(1L);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        BeanUtils.copyProperties(itemList,itemDTOList);
        Mockito.when(itemService.getAllListOfOrderedItems()).thenReturn(itemDTOList) ;
        itemController.getAllListOfOrderedItems();

    }
    @Test
    void testGetAllListOfOrderedItemsById() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        List<ItemDTO> itemDTOList=new ArrayList<>();
        Long id;
        id=1L;
        List<Item> itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        BeanUtils.copyProperties(itemList,itemDTOList);
        Mockito.when(itemService.getAllListOfOrderedItemsById(id)).thenReturn(itemDTOList) ;
        itemController.getAllListOfOrderedItemsById(id);

    }
    @Test
    void testUpdateExistingOrderItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        ItemDTO itemDTO=new ItemDTO();
        // ItemDTO itemDTO=new HashSet<>();
        Item item=new Item();
        Long itemId=3L;
        Long orderId=1L;

        Set<Item> itemList=new HashSet<>();
        itemDTO.setItemName("xyz");
        itemDTO.setItemPrice(5.0f);
        itemDTO.setItemId(itemId);
        itemDTO.setItemQuantity(3);
        itemDTO.setShippedDate("2023-98-01");
        //   itemList.add(item);
        BeanUtils.copyProperties(item,itemDTO);
        Mockito.when(itemService.updateExistingOrderItem(itemDTO ,orderId)).thenReturn(itemDTO) ;
        itemController.updateExistingOrderItem(itemDTO ,orderId);

    }
    @Test
    void testDeleteOrderedItemById() throws SQLException {
        MockitoAnnotations.initMocks(this);
        Item item=new Item();
        Long id;
        id=1L;
        Set<Item> itemList=new HashSet<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        String s1 = itemService.deleteOrderedItemById(id) ;
        itemController.deleteOrderedItemById(id);

    }
    @Test
    void testAddOrderedItem() throws SQLException {
        MockitoAnnotations.initMocks(this);
        ItemDTO item=new ItemDTO();
        Long id;
        id=1L;
        Set<ItemDTO> itemList=new HashSet<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(id);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        Mockito.when(itemService.saveItem(itemList,id)).thenReturn(itemList);
        itemController.addOrderedItem(itemList,id);

    }
    @Test
    void testGetOrdersByItemId() throws SQLException {
        MockitoAnnotations.initMocks(this);
        OrderByItem orderByItem=new OrderByItem();
        List<Long> ids=List.of(1L,2L,3L);
        orderByItem.setItemId(1L);
        orderByItem.setOrderIdList(ids);
        Mockito.when(itemService.getOrderList(1L)).thenReturn(orderByItem) ;
        itemController.getOrderList(1L);

    }
}
