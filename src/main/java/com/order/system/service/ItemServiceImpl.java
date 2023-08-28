package com.order.system.service;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.repository.ItemRepository;
import com.order.system.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Set<ItemDTO> saveItem(Set<ItemDTO> itemDTO, Long id) {
          Item item=new Item();
        Set<Item>itemSet=new HashSet<>();
        Order order = orderRepository.findByOrderId(id);
        for(ItemDTO itemDTO1:itemDTO){
            item.setItemPrice(itemDTO1.getItemPrice());
            item.setItemName(itemDTO1.getItemName());
            item.setItemQuantity(itemDTO1.getItemQuantity());
            item.setItemId(itemDTO1.getItemId());
            item.setShippedDate(itemDTO1.getShippedDate());
            itemSet.add(item);
            order.setItemList(itemSet);
            orderRepository.save(order);
           // itemRepository.save(item);
        }


        BeanUtils.copyProperties(itemDTO,itemSet);
        //Order order = orderRepository.findByOrderId(id);
        //order.setOrderId(id);


        return itemDTO;
        // itemRepository.save(itemWithOrderId);
    }


    @Override
    public List<ItemDTO> getAllListOfOrderedItems() {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        List<Item> itemList = itemRepository.findAll();
        for (Item item : itemList) {

            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemId(item.getItemId());
            itemDTO.setItemQuantity(item.getItemQuantity());
            itemDTO.setItemPrice(item.getItemPrice());
            itemDTO.setItemName(item.getItemName());
            itemDTO.setShippedDate(item.getShippedDate());
            itemDTOList.add(itemDTO);

        }
        return itemDTOList;

    }
    @Override
    public List<ItemDTO> getAllListOfOrderedItemsById(Long id) {
        //  Item item = new Item();
        List<ItemDTO> itemDTOList = new ArrayList<>();
       // List<Item> itemSet = new ArrayList<>();
        List<Item> itemList = itemRepository.findAll();
        for (Item item : itemList) {
            if (item.getItemId().equals(id)) {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setItemId(item.getItemId());
                itemDTO.setItemQuantity(item.getItemQuantity());
                itemDTO.setItemPrice(item.getItemPrice());
                itemDTO.setItemName(item.getItemName());
                itemDTO.setShippedDate(item.getShippedDate());
                itemDTOList.add(itemDTO);
            }
            //return itemDTOList;
        }
        return itemDTOList;
    }


    @Override
    public ItemDTO updateExistingOrderItem(ItemDTO newItem, Long orderid) throws SQLException {
        Set<Item> itemSet = new HashSet<>();
        ItemDTO itemDTO = new ItemDTO();
        Order order = orderRepository.findByOrderId(orderid);
        if (order != null) {
            //  Order orderWithItemList = getOrderWithId(orderid);
            Set<Item> itemList = order.getItemList().stream().collect(Collectors.toSet());
            Set<Long> itemIdList = itemList.stream().map(x -> x.getItemId()).collect(Collectors.toSet());
               if(itemList!=null){
            for (Item it : itemList) {
                if (newItem.getItemId().equals(it.getItemId())) {


                    Item updatedItem = itemRepository.findByItemId(it.getItemId());
                    if (updatedItem != null) {
                        if (itemIdList.contains(newItem.getItemId())) {
                            updatedItem.setItemId(newItem.getItemId());
                            updatedItem.setItemName(newItem.getItemName());
                            updatedItem.setItemQuantity(newItem.getItemQuantity());
                            updatedItem.setItemPrice(newItem.getItemPrice());
                            updatedItem.setShippedDate(newItem.getShippedDate());
                            itemRepository.save(updatedItem);
                            BeanUtils.copyProperties(it, itemDTO);
                            return itemDTO;
                        }
                    }
                }
            }

        }}
        return itemDTO;
    }
    @Override
    public String deleteOrderedItemById(Long id) {
        itemRepository.deleteById(id);
        return "Item deleted";
    }


    @Override
    public OrderByItem getOrderList(Long itemId) {
        OrderByItem orderByItem =new OrderByItem();
        List<Long>ids= orderRepository.findOrdersByItemId(itemId);
        orderByItem.setItemId(itemId);
        orderByItem.setOrderIdList(ids);
        return orderByItem;
    }
}
