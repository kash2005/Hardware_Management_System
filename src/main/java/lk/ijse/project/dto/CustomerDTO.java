package lk.ijse.project.dto;

import lombok.*;
import org.w3c.dom.Text;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {

    private String custId;
    private String name;
    private String address;
    private String contactNo;
    private String date;
}
