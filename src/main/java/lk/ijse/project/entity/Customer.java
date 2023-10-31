package lk.ijse.project.entity;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Customer {

    private String custId;
    private String name;
    private String address;
    private String contactNo;
    private String date;
}
