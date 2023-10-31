package lk.ijse.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SupplierDTO {
    private String supplierId;
    private String name;
    private String company;
    private String contactNo;
}
