package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.PlaceOrderDAO;
import lk.ijse.project.entity.PlaceOrder;

import java.sql.SQLException;
import java.util.List;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {
    @Override
    public boolean save(PlaceOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PlaceOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PlaceOrder searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<PlaceOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
