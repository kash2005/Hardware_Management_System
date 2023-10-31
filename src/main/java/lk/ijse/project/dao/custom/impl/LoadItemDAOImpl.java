package lk.ijse.project.dao.custom.impl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.*;
import lk.ijse.project.db.DBConnection;
import lk.ijse.project.entity.Invoice;
import lk.ijse.project.entity.Load;
import lk.ijse.project.entity.SupplyOrder;
import lk.ijse.project.entity.SupplyOrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoadItemDAOImpl implements LoadItemDAO {
    SupplierOrderDetailsDAO supplierOrderDetailsDAO = (SupplierOrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER_DETAILS);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    InvoiceDAO invoiceDAO = (InvoiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVOICE);
    HavePayInvoiceDAO havePayInvoiceDAO = (HavePayInvoiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HAVEPAYiNVOICE);
    @Override
    public boolean save(SupplyOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupplyOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SupplyOrder searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<SupplyOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextLoad() throws SQLException, ClassNotFoundException {
        String lastLoadId=generateLoadId();
        if(lastLoadId==null){
            return "L001";
        }else{
            String[] split=lastLoadId.split("[L]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newLoadId=String.format("L%03d", lastDigits);
            return newLoadId;
        }
    }

    @Override
    public boolean placeLoad(SupplyOrder supplier_oder, Invoice invoice, SupplyOrderDetails details) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            boolean save = save(supplier_oder);
            if (save) {
                System.out.println("Done in supplyorder");
                boolean saveOrderDetail = supplierOrderDetailsDAO.save(details);
                if (saveOrderDetail) {
                    System.out.println("Done in supplies");
                    boolean updateLoadQty = itemDAO.updateLoadQty(details);
                    if (updateLoadQty) {
                        System.out.println("DOne in item");
                        String status = supplier_oder.getStatus();
                        boolean saveinvoice = false;
                        boolean save1 = false;
                        if (status.equals("Invoice")) {
                            saveinvoice = havePayInvoiceDAO.save(invoice);
                        } else {
                            save1 = invoiceDAO.save(invoice);
                        }
                        if ((saveinvoice) || (save1)) {
                            DBConnection.getDbConnection().getConnection().commit();
                            return true;
                        }
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Not update in item", ButtonType.OK).show();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Not Added to supplies", ButtonType.OK).show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Not Added to suppliyorder", ButtonType.OK).show();
            }
            DBConnection.getDbConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }

    private String generateLoadId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT supplyOrderId FROM supplyorder ORDER BY supplyOrderId DESC LIMIT 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
