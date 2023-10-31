package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.OrderDAO;
import lk.ijse.project.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders(OrderId,custId,date,price,delivery_status) VALUES(?,?,?,?,?) ";

        return SQLUtil.execute(sql,entity.getOrderId(),entity.getDate(),entity.getPrice(),entity.getDelivaryStatus());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Orders searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String totalSales() throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(price) FROM orders";
        ResultSet resultSet= SQLUtil.execute(sql);

        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return "000.00";
    }

    @Override
    public String todaySales(LocalDate date) throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(price) FROM orders WHERE date = ?";
        ResultSet resultSet=SQLUtil.execute(sql, date);

        if(resultSet.next()){
            return String.valueOf(resultSet.getDouble(1));
        }
        return "000.00";
    }


    @Override
    public int orderCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(orderId) FROM orders";
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        String lastOrderId=generateOrderId();
        if(lastOrderId==null){
            return "O001";
        }else{
            String[] split=lastOrderId.split("[O]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newOrderId=String.format("O%03d", lastDigits);
            return newOrderId;
        }
    }

    @Override
    public String generateDeliveryId() throws SQLException, ClassNotFoundException {
        String sql = "select D_id from delivery order by D_id desc limit 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            return nextDeliveryId(id);
        }
        return nextDeliveryId(null);
    }

    private String nextDeliveryId(String id) {
        String generated = "D001";
        if (id != null){
            String[] strings = id.split("D");
            int index = Integer.parseInt(strings[1]);
            index++;
            generated = String.format("D%03d", index);
            return generated;
        }
        return generated;
    }

    private String generateOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
