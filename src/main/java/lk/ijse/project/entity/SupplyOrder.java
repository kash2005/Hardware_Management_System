package lk.ijse.project.entity;

import lk.ijse.project.dto.DetailDTO;
import lombok.*;

import java.util.ArrayList;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SupplyOrder {
    private String soi_id;
    private String sup_id;
    private String date;
    private double amount;
    private String status;
    private ArrayList<DetailDTO> details = new ArrayList<>();
}
