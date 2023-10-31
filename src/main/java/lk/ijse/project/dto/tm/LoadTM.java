package lk.ijse.project.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoadTM {
    private String loadId;
    private int qty;
    private double price;
    private Button remove;
}
