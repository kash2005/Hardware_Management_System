package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.dto.DeliveryDTO;

import java.sql.SQLException;
import java.util.List;

public interface DeliveryBO extends SuperBO {
    List<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException;
}
