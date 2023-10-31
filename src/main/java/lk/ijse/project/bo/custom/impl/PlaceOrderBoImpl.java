package lk.ijse.project.bo.custom.impl;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.project.bo.custom.PlaceOrderBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.*;
import lk.ijse.project.db.DBConnection;
import lk.ijse.project.dto.DeliveryDTO;
import lk.ijse.project.entity.OrderDetails;
import lk.ijse.project.dto.PlaceOrderDTO;
import lk.ijse.project.entity.Delivery;
import lk.ijse.project.entity.Orders;
import lk.ijse.project.entity.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlaceOrderBoImpl implements PlaceOrderBO {

    PlaceOrderDAO placeOrderDAO= (PlaceOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PLACE_ORDER);

    OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);


    @Override
    public boolean placeOrder(PlaceOrderDTO placeOrder, DeliveryDTO deliveryDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDbConnection().getConnection().setAutoCommit(false);
            orderDAO.save(new Orders(placeOrder.getCode(), deliveryDTO.getCId(), LocalDate.now(), placeOrder.getUnitPrice(), "NO"));
            boolean saveOrder = placeOrderDAO.save(new PlaceOrder(placeOrder.getCode(),
                    placeOrder.getDescription(),
                    placeOrder.getUnitPrice(),
                    placeOrder.getQty(),
                    placeOrder.getTotal()
            ));

            if (saveOrder) {
                System.out.println("Done 1");
                boolean orderDetailsOK = orderDetailsDAO.save(new OrderDetails(
                        placeOrder.getCode(),
                        deliveryDTO.getOId(),
                        placeOrder.getQty()
                ));
                if (orderDetailsOK) {
                    System.out.println("Done 4");
                    DBConnection.getDbConnection().getConnection().commit();
                    return true;
                }
            } else {
                System.out.println("Done 5");
                DBConnection.getDbConnection().getConnection().commit();
                return true;
            }
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            DBConnection.getDbConnection().getConnection().rollback();
            throw new RuntimeException(e);
        } finally {
            DBConnection.getDbConnection().getConnection().setAutoCommit(true);
        }
    }
}
