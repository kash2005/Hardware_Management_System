package lk.ijse.project.entity;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String itemCode;
    private String name;
    private String itemType;
    private double sellPrice;
    private double getPrice;
    private int quantity;
}
