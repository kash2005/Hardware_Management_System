package lk.ijse.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DeliveryDTO {
    private String dId;
    private String oId;
    private String vId;
    private String cId;
    private int distance;
    private double amount;
}
