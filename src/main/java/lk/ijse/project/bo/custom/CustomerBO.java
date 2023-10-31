package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomerId(String id) throws SQLException, ClassNotFoundException;

    Integer[] getCustomerValueMonths() throws SQLException, ClassNotFoundException;

    int customerCount() throws SQLException, ClassNotFoundException;
}
