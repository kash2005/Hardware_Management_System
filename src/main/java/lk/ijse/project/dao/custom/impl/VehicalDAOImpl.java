package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.VehicleDAO;
import lk.ijse.project.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicalDAOImpl implements VehicleDAO {
    @Override
    public boolean save(Vehicle entity) throws SQLException, ClassNotFoundException {
        String sql = "insert into vehicle(vehcleId,vehicleType,vehicleNo,status) values(?,?,?,?)";
        return SQLUtil.execute(sql,entity.getVehicleId(),entity.getVehicleType(),entity.getVehicleNo(),entity.getStatus());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from vehicle where vehicleId = ? ";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public boolean update(Vehicle entity) throws SQLException, ClassNotFoundException {
        String sql = "update vehicle set vehicleType = ?, vehicleNo = ?, status =? where vehicleId = ?";
        return SQLUtil.execute(sql,entity.getVehicleType(),entity.getVehicleNo(),entity.getStatus(),entity.getVehicleId());
    }

    @Override
    public Vehicle searchId(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from vehicle where vehicleId = ?";
        ResultSet resultSet = SQLUtil.execute(sql,id);
        if (resultSet.next()){
            String vehicle_id = resultSet.getString(1);
            String vehicle_type = resultSet.getString(2);
            String vehicle_no = resultSet.getString(3);
            String status = resultSet.getString(4);
            return new Vehicle(vehicle_id,vehicle_type,vehicle_no,status);
        }
        return null;
    }

    @Override
    public List<Vehicle> getAll() throws SQLException, ClassNotFoundException {
            String sql = "select * from vehicle";
            List<Vehicle> data = new ArrayList<>();
            ResultSet resultSet = SQLUtil.execute(sql);
            while (resultSet.next()){
                data.add(new Vehicle(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
            }
            return data;
    }
}
