package lk.ijse.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Supplier {
    private String supplierId;
    private String name;
    private String company;
    private String contactNo;
}
