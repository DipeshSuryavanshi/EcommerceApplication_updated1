package com.order.system.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderByItem {
  private String msg="Get Order List By Item Id ";
  private Long itemId;
  private List<Long> orderIdList;


}
