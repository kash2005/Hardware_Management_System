package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.dto.DeliveryDTO;
import lk.ijse.project.dto.PlaceOrderDTO;
import lk.ijse.project.entity.PlaceOrder;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {

    boolean placeOrder(PlaceOrderDTO placeOrder, DeliveryDTO d1) throws SQLException, ClassNotFoundException;
}
