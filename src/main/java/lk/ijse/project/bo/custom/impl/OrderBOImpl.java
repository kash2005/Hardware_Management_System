package lk.ijse.project.bo.custom.impl;

import lk.ijse.project.bo.custom.OrderBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.OrderDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String getTotalSales() throws SQLException, ClassNotFoundException {
        return orderDAO.totalSales();
    }

    @Override
    public String getTodaySales(LocalDate date) throws SQLException, ClassNotFoundException {
        return orderDAO.todaySales(date);
    }

    @Override
    public int orderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.orderCount();
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextId();
    }

    @Override
    public String generNextDeliveryId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateDeliveryId();
    }
}
