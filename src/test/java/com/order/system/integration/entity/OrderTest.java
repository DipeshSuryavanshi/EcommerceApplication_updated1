package com.order.system.integration.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class OrderTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String customerName;
    private String customerAddress;

    private float orderAmount;

    private Integer numberOfItems;

}
