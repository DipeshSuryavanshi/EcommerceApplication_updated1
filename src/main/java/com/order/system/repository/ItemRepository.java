package com.order.system.repository;

import com.order.system.entity.Item;
import com.order.system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static org.hibernate.sql.ast.Clause.SELECT;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
     public Item findByItemId(Long id);

//     @Query( value = "SELECT i.orderId,i.itemName FROM Item i WHERE i.itemId = :itemId",nativeQuery = true)
//    // @Query("SELECT i.order FROM Item i WHERE i.id = :itemId")
//     List<Order> findOrdersByItemId(Long itemId);


}
