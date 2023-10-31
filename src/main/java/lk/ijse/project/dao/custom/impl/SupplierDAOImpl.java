package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.SupplierDAO;
import lk.ijse.project.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplier(supplierId,name,company,contactNo) VALUES (?,?,?,?)";
        return SQLUtil.execute(sql,entity.getSupplierId(),entity.getName(),entity.getCompany(),entity.getContactNo());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM supplier WHERE supplierId=?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE supplier SET name=?, company=?, contactNo=? WHERE supplierId=?";
        return SQLUtil.execute(sql,entity.getName(),entity.getCompany(),entity.getContactNo(),entity.getSupplierId());
    }

    @Override
    public Supplier searchId(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier WHERE supplierId=?;";
        ResultSet result = SQLUtil.execute(sql, id);
        if (result.next()){
            String supplierId = result.getString(1);
            String name = result.getString(2);
            String company = result.getString(3);
            String contactNo = result.getString(4);

            return new Supplier(supplierId,name,company,contactNo);
        }
        return null;
    }

    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier";

        List<Supplier> data = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()){
            data.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            ));
        }
        return data;
    }
}
