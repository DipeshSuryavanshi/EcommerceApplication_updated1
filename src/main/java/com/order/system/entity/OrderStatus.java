package com.order.system.entity;

import com.order.system.dto.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "order_status")
public class OrderStatus   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="status_id")
    private Long statusId;
    @Column(name="order_id")
    private Long orderId;
    private Status orderStatus;


}
