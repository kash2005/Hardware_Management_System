package lk.ijse.project.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class VehicleTM {
    private String vehicleId;
    private String vehicleType;
    private String vehicleNo;
    private String status;
}