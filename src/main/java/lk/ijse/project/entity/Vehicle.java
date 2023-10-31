package lk.ijse.project.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Vehicle {
    private String vehicleId;
    private String vehicleType;
    private String vehicleNo;
    private String status;
}
