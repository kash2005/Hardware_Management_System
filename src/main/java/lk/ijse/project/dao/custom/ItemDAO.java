package lk.ijse.project.dao.custom;

import lk.ijse.project.dao.CrudDAO;
import lk.ijse.project.entity.Item;
import lk.ijse.project.entity.SupplyOrder;
import lk.ijse.project.entity.SupplyOrderDetails;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    String generateNextId() throws SQLException, ClassNotFoundException;

    boolean updateLoadQty(SupplyOrderDetails details) throws SQLException, ClassNotFoundException;
}
