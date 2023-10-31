package lk.ijse.project.dao;

import lk.ijse.project.bo.custom.impl.ItemBOImpl;
import lk.ijse.project.bo.custom.impl.VehicleBOImpl;
import lk.ijse.project.dao.custom.EmployeeDAO;
import lk.ijse.project.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        if (daoFactory == null){
            return daoFactory = new DAOFactory();
        }else {
            return daoFactory;
        }
    }

    public enum DAOTypes{
        CUSTOMER,EMPLOYEE,SUPPLIER,VEHICLE,ITEM,DELIVERY,USER,ORDER,LOAD,PLACE_ORDER,SUPPLIER_ORDER_DETAILS,INVOICE,HAVEPAYiNVOICE,ORDERDETAILS
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case VEHICLE:
                return new VehicalDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case DELIVERY:
                return new DeliveryDAOImpl();
            case USER:
                return new UserDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case LOAD:
                return new LoadItemDAOImpl();
            case PLACE_ORDER:
                return new PlaceOrderDAOImpl();
            case SUPPLIER_ORDER_DETAILS:
                return new SupplierOrderDetailsDAOImpl();
            case HAVEPAYiNVOICE:
                return new HavePayInvoiceDAOImpl();
            case INVOICE:
                return new ItemDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }
}
