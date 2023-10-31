package lk.ijse.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Delivery {
    private String dId;
    private String oId;
    private String vId;
    private String cId;
    private int distance;
    private double amount;
}
