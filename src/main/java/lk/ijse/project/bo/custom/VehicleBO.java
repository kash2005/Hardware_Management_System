package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.dao.CrudDAO;
import lk.ijse.project.dto.VehicleDTO;
import lk.ijse.project.entity.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleBO extends SuperBO {
    boolean saveVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;

    boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException;

    boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException;

    List<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException;

    VehicleDTO searchVehicle(String vehicleId) throws SQLException, ClassNotFoundException;
}
