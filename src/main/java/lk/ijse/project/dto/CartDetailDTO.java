package lk.ijse.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartDetailDTO {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;
}
