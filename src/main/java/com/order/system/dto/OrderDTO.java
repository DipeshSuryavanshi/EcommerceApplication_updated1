package com.order.system.dto;

import com.order.system.entity.Item;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Data
public class OrderDTO {

    private Long orderId;
    private LocalDate orderDate;
    private String customerName;
    private Long customerId;
    private String customerAddress;
    private String dispatchDate;
    private float orderAmount;
    private Integer numberOfItems;
    private Status status;
    private Set<Item> itemList ;

}
