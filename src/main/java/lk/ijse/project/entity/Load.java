package lk.ijse.project.entity;

import javafx.scene.control.Button;
import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Load {
    private String loadId;
    private int qty;
    private double price;
    private Button remove;
}
