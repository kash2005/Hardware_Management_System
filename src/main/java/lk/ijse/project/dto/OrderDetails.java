package lk.ijse.project.dto;

import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetails {
    private String itemCode;
    private String orderId;
    private int qty;
}
