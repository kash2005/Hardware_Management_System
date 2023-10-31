package lk.ijse.project.dto.tm;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemTM {
    private String itemCode;
    private String name;
    private String itemType;
    private double sellPrice;
    private int quantity;
}
