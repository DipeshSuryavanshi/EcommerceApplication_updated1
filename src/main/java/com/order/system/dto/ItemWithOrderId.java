package com.order.system.dto;

import com.order.system.entity.Item;
import lombok.Data;

import java.util.List;

@Data
public class ItemWithOrderId {
    private Long orderId;
    private Item item;
}
