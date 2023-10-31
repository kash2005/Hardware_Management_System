package lk.ijse.project.dto;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlaceOrderDTO {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;
    private Button btndelete;
}
