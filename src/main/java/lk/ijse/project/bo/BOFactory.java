package lk.ijse.project.bo;

import lk.ijse.project.bo.custom.SupplierBO;
import lk.ijse.project.bo.custom.impl.*;
import lk.ijse.project.dao.custom.impl.PlaceOrderDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory(){
       // return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
        if (boFactory == null){
           return boFactory = new BOFactory();
        }else {
            return boFactory;
        }
    }

    public enum BOTypes{
        CUSTOMER,EMPLOYEE,SUPPLIER,VEHICLE,ITEM,DELIVERY,USER,ORDER,LOAD,PLACE_ORDER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case DELIVERY:
                return new DelliveryBOImpl();
            case USER:
                return new UserBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case LOAD:
                return new LoadItemBoImpl();
            case PLACE_ORDER:
                return new PlaceOrderBoImpl();
            default:
                return null;
        }
    }
}
