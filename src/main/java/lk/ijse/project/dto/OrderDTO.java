package lk.ijse.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderDTO {
    private String orderId;
    private String custId;
    private String date;
    private String price;
    private String delivaryStatus;
}