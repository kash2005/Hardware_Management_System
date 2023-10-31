package lk.ijse.project.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderTM {
    private String orderId;
    private String custId;
    private String date;
    private String price;
    private String delivaryStatus;
}