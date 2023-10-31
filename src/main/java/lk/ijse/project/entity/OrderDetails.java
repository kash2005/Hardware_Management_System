package lk.ijse.project.entity;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetails {
    private String itemId;
    private String orderId;
    private int quantity;
}
