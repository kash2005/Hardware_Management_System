package lk.ijse.project.bo.custom.impl;

import lk.ijse.project.bo.custom.LoadItemBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.LoadItemDAO;
import lk.ijse.project.dto.DetailDTO;
import lk.ijse.project.entity.Invoice;
import lk.ijse.project.entity.SupplyOrder;
import lk.ijse.project.entity.SupplyOrderDetails;

import java.sql.SQLException;

public class LoadItemBoImpl implements LoadItemBO {

    LoadItemDAO loadItemDAO = (LoadItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOAD);

    @Override
    public String generateNextLoadId() throws SQLException, ClassNotFoundException {
        return loadItemDAO.generateNextLoad();
    }

    @Override
    public boolean PlaceLoad(SupplyOrder supplier_oder, Invoice invoice, DetailDTO details) throws SQLException, ClassNotFoundException {
        return loadItemDAO.placeLoad(supplier_oder,invoice,new SupplyOrderDetails(details.getSo_id(),details.getCode(),details.getQty(),details.getPrice()));
    }
}
