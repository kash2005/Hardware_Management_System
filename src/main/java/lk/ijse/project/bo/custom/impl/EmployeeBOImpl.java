package lk.ijse.project.bo.custom.impl;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.bo.custom.EmployeeBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.EmployeeDAO;
import lk.ijse.project.dto.CustomerDTO;
import lk.ijse.project.dto.EmployeeDTO;
import lk.ijse.project.entity.Customer;
import lk.ijse.project.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO{

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);


    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDTO.getEId(),employeeDTO.getEName(),employeeDTO.getEAddress(),employeeDTO.getEJob(),employeeDTO.getESalary(),employeeDTO.getEContactNo()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDTO.getEId(),employeeDTO.getEName(),employeeDTO.getEAddress(),employeeDTO.getEJob(),employeeDTO.getESalary(),employeeDTO.getEContactNo()));
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        List<EmployeeDTO> allEmployees = new ArrayList<>();
        List<Employee> all = employeeDAO.getAll();
        for (Employee employee:all) {
            allEmployees.add(new EmployeeDTO(employee.getEId(),employee.getEName(),employee.getEAddress(),employee.getEJob(),employee.getESalary(),employee.getEContactNo()));
        }
        return allEmployees;
    }

    @Override
    public EmployeeDTO searchEmployeeId(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchId(id);
        return new EmployeeDTO(employee.getEId(),employee.getEName(),employee.getEAddress(),employee.getEJob(),employee.getESalary(),employee.getEContactNo());
    }

    @Override
    public String generateNextEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.genetateNextId();
    }
}
