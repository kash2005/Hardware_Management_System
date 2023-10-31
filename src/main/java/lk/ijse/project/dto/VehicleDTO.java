package lk.ijse.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class VehicleDTO {
    private String vehicleId;
    private String vehicleType;
    private String vehicleNo;
    private String status;
}
