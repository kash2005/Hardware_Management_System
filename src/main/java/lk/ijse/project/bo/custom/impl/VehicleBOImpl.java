package lk.ijse.project.bo.custom.impl;

import lk.ijse.project.bo.custom.VehicleBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.VehicleDAO;
import lk.ijse.project.dto.CustomerDTO;
import lk.ijse.project.dto.VehicleDTO;
import lk.ijse.project.entity.Customer;
import lk.ijse.project.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);


    @Override
    public boolean saveVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException {
        return vehicleDAO.save(new Vehicle(vehicleDTO.getVehicleId(), vehicleDTO.getVehicleType(), vehicleDTO.getVehicleNo(), vehicleDTO.getStatus()));
    }

    @Override
    public boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(id);
    }

    @Override
    public boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Vehicle(vehicleDTO.getVehicleId(),vehicleDTO.getVehicleType(),vehicleDTO.getVehicleNo(),vehicleDTO.getStatus()));
    }

    @Override
    public List<VehicleDTO> getAllVehicle() throws SQLException, ClassNotFoundException {
        List<VehicleDTO> allVehicles = new ArrayList<>();
        List<Vehicle> all = vehicleDAO.getAll();
        for (Vehicle vehicle:all) {
            allVehicles.add(new VehicleDTO(vehicle.getVehicleId(),vehicle.getVehicleType(),vehicle.getVehicleNo(),vehicle.getStatus()));
        }
        return allVehicles;
    }

    @Override
    public VehicleDTO searchVehicle(String vehicleId) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = vehicleDAO.searchId(vehicleId);
        return new VehicleDTO(vehicle.getVehicleId(),vehicle.getVehicleType(),vehicle.getVehicleNo(),vehicle.getStatus());
    }
}
