package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.SupplierOrderDetailsDAO;
import lk.ijse.project.entity.SupplyOrderDetails;

import java.sql.SQLException;
import java.util.List;

public class SupplierOrderDetailsDAOImpl implements SupplierOrderDetailsDAO {
    @Override
    public boolean save(SupplyOrderDetails entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO supplies VALUES (?,?,?,?)";
        return SQLUtil.execute(sql,entity.getSo_id(),entity.getCode(),entity.getQty(),entity.getPrice());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupplyOrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SupplyOrderDetails searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<SupplyOrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
