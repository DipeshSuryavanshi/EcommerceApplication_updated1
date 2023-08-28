package com.order.system.service;

import com.order.system.dto.ItemDTO;
import com.order.system.dto.OrderByItem;
import com.order.system.entity.Item;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface ItemService {
    public Set<ItemDTO> saveItem(Set<ItemDTO> itemDTO, Long id);
    public List<ItemDTO> getAllListOfOrderedItems();
    public List<ItemDTO> getAllListOfOrderedItemsById(Long id);
    public ItemDTO updateExistingOrderItem(ItemDTO itemDTO, Long orderId) throws SQLException;
    public String deleteOrderedItemById(Long id);
    public OrderByItem getOrderList(Long itemId);

}
