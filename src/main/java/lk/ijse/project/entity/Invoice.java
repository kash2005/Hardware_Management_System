package lk.ijse.project.entity;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private String ID;
    private String date;
    private String SupId;
    private double amount;
}
