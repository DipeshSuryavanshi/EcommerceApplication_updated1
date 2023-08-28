package com.order.system.controller;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.service.ItemServiceImpl;
import com.order.system.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ItemController {
//    @Autowired
//    OrderServiceImpl orderProcessingService;
    @Autowired
    ItemServiceImpl itemServiceImpl;



    @GetMapping (value="/order-items")
    public ResponseEntity<List<ItemDTO>> getAllListOfOrderedItems() throws SQLException {
        List<ItemDTO> itemListDTO=	itemServiceImpl.getAllListOfOrderedItems();
        return new ResponseEntity<>(itemListDTO, HttpStatus.OK);
    }
    @GetMapping (value="/order-items/{id}")
    public ResponseEntity<List<ItemDTO>> getAllListOfOrderedItemsById(@PathVariable Long id) throws SQLException {
        List<ItemDTO> itemListDTO=	itemServiceImpl.getAllListOfOrderedItemsById(id);
        return new ResponseEntity<>(itemListDTO, HttpStatus.OK);
    }
    @PutMapping (value="/order-items/{orderid}")
    public ResponseEntity<ItemDTO> updateExistingOrderItem( @RequestBody ItemDTO itemDTO , @PathVariable Long orderid) throws SQLException {
        ItemDTO item=	itemServiceImpl.updateExistingOrderItem(itemDTO,orderid);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @DeleteMapping (value="/order-items/{id}")
    public ResponseEntity<String> deleteOrderedItemById(@PathVariable Long id) throws SQLException {
        itemServiceImpl.deleteOrderedItemById(id);
        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
    }
    @PostMapping (value="/order-items/{id}")
    public ResponseEntity<Set<ItemDTO>> addOrderedItem(@RequestBody Set<ItemDTO> itemDTO, @PathVariable Long id ) throws SQLException {
        Order order=new Order();
        Set<ItemDTO> itemSet=itemServiceImpl.saveItem(itemDTO,id);
        return new ResponseEntity<>(itemSet, HttpStatus.OK);
    }

    @GetMapping("/orders-by-item/{itemId}")
    public OrderByItem getOrderList(@PathVariable Long itemId) {
        //return orderProcessingService.findOrdersByItemId(itemId);
        OrderByItem orderByItem=itemServiceImpl.getOrderList(itemId);
        return orderByItem;
    }


}
