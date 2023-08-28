package com.order.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "item_detail")

public class Item  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="item_id")
    private Long itemId;
    @Column(name="item_name")
    private String itemName;
    @Column(name="item_price")
    private float itemPrice;
    @Column(name="item_quantity")
    private Integer itemQuantity;
    @Column(name="shipped_date")
    private String shippedDate;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }


    @OneToMany(cascade=CascadeType.ALL)
  //  @JoinColumn(name = "order_id")
    private Set<Order> order;

}
