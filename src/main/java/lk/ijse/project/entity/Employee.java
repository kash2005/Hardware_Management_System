package lk.ijse.project.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private String eId;
    private String eName;
    private String eAddress;
    private String eJob;
    private Double eSalary;
    private String eContactNo;
}
