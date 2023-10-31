package lk.ijse.project.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Orders {
    private String orderId;
    private String custId;
    private LocalDate date;
    private double price;
    private String delivaryStatus;
}
