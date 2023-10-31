package lk.ijse.project.dto;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetailDTO {
    private String so_id;
    private String code;
    private int qty;
    private double price;
}
