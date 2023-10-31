package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.custom.OrderDetailsDAO;
import lk.ijse.project.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetails searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
