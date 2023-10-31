package lk.ijse.project.entity;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlaceOrder {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;
}
