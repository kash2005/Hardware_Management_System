package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.InvoiceDAO;
import lk.ijse.project.entity.Invoice;

import java.sql.SQLException;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {
    @Override
    public boolean save(Invoice entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO invoice VALUES(?,?,?,?) ";
        return SQLUtil.execute(sql,entity.getID(),entity.getDate(),entity.getSupId(),entity.getAmount());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Invoice entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Invoice searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Invoice> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
