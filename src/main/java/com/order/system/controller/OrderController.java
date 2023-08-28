package com.order.system.controller;

import com.order.system.dto.OrderDTO;
import com.order.system.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderServiceImpl orderProcessingService;


    @PostMapping(value="/orders", consumes = "application/json")
    public ResponseEntity<OrderDTO> addOrderWithItems(@RequestBody OrderDTO order) throws SQLException {
        OrderDTO orderDto=	orderProcessingService.saveOrderWithItems(order);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
    @GetMapping (value="/orders")
    public ResponseEntity<List<OrderDTO>> getOrderWithItems() throws SQLException {
       List<OrderDTO> OrderList=	orderProcessingService.getOrderWithItems();
        return new ResponseEntity<>(OrderList, HttpStatus.OK);
    }
    @GetMapping (value="/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderWithId(@PathVariable Long id) throws SQLException {
        OrderDTO Order=	orderProcessingService.getOrderWithId(id);
        return new ResponseEntity<>(Order, HttpStatus.OK);
    }
    @PutMapping (value="/orders/{orderId}")
    public ResponseEntity<String> updateOrderWithItems(@RequestBody OrderDTO orderDTO, @PathVariable Long orderId ) throws SQLException {
      String response=	orderProcessingService.updateOrderWithItems(orderDTO,orderId);
      if(response=="Order Updated Successfully"){
          return new ResponseEntity<>("Order Updated Successfully", HttpStatus.OK);
      }
        return new ResponseEntity<>("Order NOt Exist", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping (value="/orders/{id}")
    public ResponseEntity<String> deleteeOrderWithItems(@PathVariable Long id) throws SQLException {
        orderProcessingService.deleteeOrderWithItems(id);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }


    @PutMapping("/{orderId}/{status}")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String status) {
        orderProcessingService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Order status updated");
    }
}
