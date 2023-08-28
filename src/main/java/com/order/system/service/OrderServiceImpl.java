package com.order.system.service;

import com.order.system.dto.OrderByItem;
import com.order.system.dto.OrderDTO;
import com.order.system.dto.Status;
import com.order.system.entity.Item;
import com.order.system.entity.Order;
import com.order.system.repository.ItemRepository;
import com.order.system.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private  final Status statusDefatulValue=Status.PENDING;
    @Autowired
    OrderRepository orederRepository;
    @Autowired
    ItemRepository itemRepository;

    public OrderDTO saveOrderWithItems(OrderDTO orderDTO) {
        Order order=new Order();
      //  order.setStatus(statusDefatulValue);

        orderDTO.setStatus(statusDefatulValue);
        order.setItemList(orderDTO.getItemList());
        BeanUtils.copyProperties(orderDTO,order);
         orederRepository.save(order);
        orderDTO.setOrderId(order.getOrderId());
        BeanUtils.copyProperties(orderDTO,order);
         return orderDTO;

    }


    // BeanUtils.copyProperties(item,itemDTO);


    public List<OrderDTO> getOrderWithItems() {
        List<OrderDTO>orderDTOList=new ArrayList<>();

        List<Order> orderList= orederRepository.findAll();
        for(Order order:orderList){
            OrderDTO orderDTO=new OrderDTO();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setOrderAmount(order.getOrderAmount());
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setItemList(order.getItemList());
            orderDTO.setCustomerId(orderDTO.getCustomerId());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setCustomerAddress(order.getCustomerAddress());
            orderDTO.setDispatchDate(order.getDispatchDate());
            orderDTO.setCustomerName(order.getCustomerName());
            orderDTO.setNumberOfItems(order.getNumberOfItems());
            orderDTOList.add(orderDTO);

        }

        BeanUtils.copyProperties(orderList,orderDTOList);
        return orderDTOList;
    }

    public OrderDTO getOrderWithId(Long id) {
        OrderDTO orderDTO=new OrderDTO();
        Order order= orederRepository.findByOrderId(id);
        BeanUtils.copyProperties(order,orderDTO);
        return orderDTO;
    }

    public String updateOrderWithItems(OrderDTO orderDTO,Long orderId) {
        Order getExistOrder = orederRepository.findByOrderId(orderId);
        Order orderUpdate = new Order();
        if (getExistOrder != null) {
            orderUpdate.setOrderId(orderId);
            orderUpdate.setOrderAmount(orderDTO.getOrderAmount());
            orderUpdate.setOrderDate(orderDTO.getOrderDate());
            orderUpdate.setCustomerName(orderDTO.getCustomerName());
            orderUpdate.setCustomerAddress(orderDTO.getCustomerAddress());
            orderUpdate.setNumberOfItems(orderDTO.getNumberOfItems());
            orderUpdate.setStatus(orderDTO.getStatus());
            orderUpdate.setDispatchDate(orderDTO.getDispatchDate());
            orderUpdate.setCustomerId(orderDTO.getCustomerId());

            orederRepository.save(orderUpdate);
            return "Order Updated Successfully";

            }

            //  o.setItemList(order.getItemList());
           // orederRepository.save(orderUpdate);


        return "Order Not Exist";
    }

    public String deleteeOrderWithItems(Long id) {
        orederRepository.deleteById(id);
        return "Deleted";
    }




    public String updateOrderStatus(Long orderId, String newStatus) {
        Order order = orederRepository.findByOrderId(orderId);
        if(order!=null ) {
            order.setStatus(Status.valueOf(newStatus));
            orederRepository.save(order);
            return "Status Updated";
        }
        return "Status Updated";
    }
}
