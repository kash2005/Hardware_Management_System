package lk.ijse.project.bo.custom.impl;

import lk.ijse.project.bo.custom.DeliveryBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.DeliveryDAO;
import lk.ijse.project.dto.DeliveryDTO;
import lk.ijse.project.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DelliveryBOImpl implements DeliveryBO {

    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);

    @Override
    public List<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException {
        List<DeliveryDTO> allDelivery = new ArrayList<>();
        List<Delivery> all = deliveryDAO.getAll();
        for (Delivery delivery:all) {
            allDelivery.add(new DeliveryDTO(delivery.getOId(), delivery.getDId(), delivery.getCId(), delivery.getVId(), delivery.getDistance(), delivery.getAmount()));
        }
        return allDelivery;
    }
}
