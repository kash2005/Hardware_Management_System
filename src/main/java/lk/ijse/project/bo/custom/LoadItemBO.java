package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.dto.DetailDTO;
import lk.ijse.project.entity.Invoice;
import lk.ijse.project.entity.SupplyOrder;
import lk.ijse.project.entity.SupplyOrderDetails;

import java.sql.SQLException;

public interface LoadItemBO extends SuperBO {
    String generateNextLoadId() throws SQLException, ClassNotFoundException;

    boolean PlaceLoad(SupplyOrder supplier_oder, Invoice invoice, DetailDTO details) throws SQLException, ClassNotFoundException;
}
