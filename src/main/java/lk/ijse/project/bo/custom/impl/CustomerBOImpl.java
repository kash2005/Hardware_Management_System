package lk.ijse.project.bo.custom.impl;

import lk.ijse.project.bo.custom.CustomerBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.CustomerDAO;
import lk.ijse.project.dto.CustomerDTO;
import lk.ijse.project.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO{

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getCustId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getContactNo(), customerDTO.getDate()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getContactNo(), customerDTO.getDate()));
    }

    @Override
    public CustomerDTO searchCustomerId(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchId(id);
        return new CustomerDTO(customer.getCustId(), customer.getName(), customer.getAddress(), customer.getContactNo(), customer.getDate());
    }

    @Override
    public Integer[] getCustomerValueMonths() throws SQLException, ClassNotFoundException {
        return customerDAO.customerValueMonths();
    }

    @Override
    public int customerCount() throws SQLException, ClassNotFoundException {
        return customerDAO.customerCount();
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        List<CustomerDTO> allCustomers = new ArrayList<>();
        List<Customer> all = customerDAO.getAll();
        for (Customer customer:all) {
            allCustomers.add(new CustomerDTO(customer.getCustId(), customer.getName(), customer.getAddress(), customer.getContactNo(), customer.getDate()));
        }
        return allCustomers;
    }

}
