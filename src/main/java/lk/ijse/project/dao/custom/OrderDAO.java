package lk.ijse.project.dao.custom;

import lk.ijse.project.dao.CrudDAO;
import lk.ijse.project.entity.Orders;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDAO extends CrudDAO<Orders> {
    String totalSales() throws SQLException, ClassNotFoundException;

    String todaySales(LocalDate date) throws SQLException, ClassNotFoundException;

    int orderCount() throws SQLException, ClassNotFoundException;

    String generateNextId() throws SQLException, ClassNotFoundException;

    String generateDeliveryId() throws SQLException, ClassNotFoundException;
}
