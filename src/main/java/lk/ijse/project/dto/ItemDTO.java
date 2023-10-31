package lk.ijse.project.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private String itemCode;
    private String name;
    private String itemType;
    private double sellPrice;
    private double getPrice;
    private int quantity;
}
