package com.order.system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class ItemDTO {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private Long itemId;

    private String itemName;

    private float itemPrice;

    private Integer itemQuantity;

    private String shippedDate;
}
