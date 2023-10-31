package lk.ijse.project.dao.custom;

import lk.ijse.project.dao.CrudDAO;
import lk.ijse.project.entity.Invoice;
import lk.ijse.project.entity.Load;
import lk.ijse.project.entity.SupplyOrder;
import lk.ijse.project.entity.SupplyOrderDetails;

import java.sql.SQLException;

public interface LoadItemDAO extends CrudDAO<SupplyOrder> {
    String generateNextLoad() throws SQLException, ClassNotFoundException;

    boolean placeLoad(SupplyOrder supplier_oder, Invoice invoice, SupplyOrderDetails details) throws SQLException, ClassNotFoundException;
}
