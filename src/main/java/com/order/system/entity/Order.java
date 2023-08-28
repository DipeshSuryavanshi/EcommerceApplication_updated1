package com.order.system.entity;

import com.order.system.dto.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderId;
    @Column(name="order_date")
    @CreationTimestamp
    private LocalDate orderDate;
    @Column(name="customer_name")
    private String customerName;
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="customer_address")
    private String customerAddress;
    @Column(name="order_dispatch")
    private String dispatchDate;
    @Column(name="order_amount")
    private float orderAmount;
    @Column(name="number_of_items")
    private Integer numberOfItems;
    @Column(name="created_at")
    @UpdateTimestamp
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
   private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",referencedColumnName ="order_id",nullable = false )
    private Set<Item> itemList ;


}
