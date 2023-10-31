package lk.ijse.project.dto;

import javafx.scene.control.Button;
import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoadDTO {
    private String loadId;
    private int qty;
    private double price;
    private Button remove;
}