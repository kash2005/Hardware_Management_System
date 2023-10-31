package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.bo.custom.DeliveryBO;
import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.DeliveryDAO;
import lk.ijse.project.entity.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {
    @Override
    public boolean save(Delivery entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Delivery entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Delivery searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Delivery> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from delivery";
        List<Delivery> data = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            data.add(new Delivery(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getInt(5), resultSet.getDouble(6) ));
        }
        return data;
    }
}
