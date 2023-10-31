package lk.ijse.project.entity;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplyOrderDetails {
    private String so_id;
    private String code;
    private int qty;
    private double price;
}
