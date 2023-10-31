package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderBO extends SuperBO {
    String getTotalSales() throws SQLException, ClassNotFoundException;

    String getTodaySales(LocalDate date) throws SQLException, ClassNotFoundException;

    int orderCount() throws SQLException, ClassNotFoundException;

    String generateNextOrderId() throws SQLException, ClassNotFoundException;

    String generNextDeliveryId() throws SQLException, ClassNotFoundException;
}
