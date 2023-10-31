package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.CustomerDAO;
import lk.ijse.project.db.DBConnection;
import lk.ijse.project.dto.CustomerDTO;
import lk.ijse.project.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer(custId,name,address, contactNo,date) VALUES (?,?,?,?,?)";
        return SQLUtil.execute(sql,entity.getCustId(),entity.getName(),entity.getAddress(),entity.getContactNo(),entity.getDate());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE custId=?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET name =?, address =?, contactNo =?,date=? WHERE custId = ?";
        return SQLUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getContactNo(),entity.getDate(),entity.getCustId());
    }

    @Override
    public Customer searchId(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE custId = ?";
        ResultSet result = SQLUtil.execute(sql, id);
        if (result.next()){
            String customer_id = result.getString(1);
            String customer_name = result.getString(2);
            String customer_address = result.getString(3);
            String customerContact = result.getString(4);
            String date = result.getString(5);

            return new Customer(customer_id,customer_name,customer_address,customerContact,date);
        }
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM customer";

        List<Customer> data = new ArrayList<>();
        ResultSet resultSet =SQLUtil.execute(sql);

        while (resultSet.next()){
            data.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return data;
    }

    @Override
    public Integer[] customerValueMonths() throws SQLException, ClassNotFoundException {
        Integer[] data = new Integer[12];
        int jan = 0;
        int feb = 0;
        int mar = 0;
        int apr = 0;
        int may = 0;
        int jun = 0;
        int jul = 0;
        int aug = 0;
        int sep = 0;
        int oct = 0;
        int nov = 0;
        int dec = 0;

        String sql = "SELECT MONTH(date), COUNT(custId) FROM customer GROUP BY MONTH(date) ";

        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()) {
            switch (resultSet.getString(1)) {

                case "1":
                    jan = Integer.parseInt(resultSet.getString(2));
                    break;
                case "2":
                    feb = Integer.parseInt(resultSet.getString(2));
                    break;
                case "3":
                    mar = Integer.parseInt(resultSet.getString(2));
                    break;
                case "4":
                    apr = Integer.parseInt(resultSet.getString(2));
                    break;
                case "5":
                    may = Integer.parseInt(resultSet.getString(2));
                    break;
                case "6":
                    jun = Integer.parseInt(resultSet.getString(2));
                    break;
                case "7":
                    jul = Integer.parseInt(resultSet.getString(2));
                    break;
                case "8":
                    aug = Integer.parseInt(resultSet.getString(2));
                    break;
                case "9":
                    sep = Integer.parseInt(resultSet.getString(2));
                    break;
                case "10":
                    oct = Integer.parseInt(resultSet.getString(2));
                    break;
                case "11":
                    nov = Integer.parseInt(resultSet.getString(2));
                    break;
                case "12":
                    dec = Integer.parseInt(resultSet.getString(2));
                    break;

            }
            data[0] = jan;
            data[1] = feb;
            data[2] = mar;
            data[3] = apr;
            data[4] = may;
            data[5] = jun;
            data[6] = jul;
            data[7] = aug;
            data[8] = sep;
            data[9] = oct;
            data[10] = nov;
            data[11] = dec;

        }

        return data;

    }

    @Override
    public int customerCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(custId) FROM customer";
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}