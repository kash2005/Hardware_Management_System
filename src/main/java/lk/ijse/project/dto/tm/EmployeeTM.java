package lk.ijse.project.dto.tm;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeTM {
    private String id;
    private String name;
    private String address;
    private String job;
    private Double salary;
    private String ContactNo;
}
