package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    EmployeeDTO searchEmployeeId(String id) throws SQLException, ClassNotFoundException;

    String generateNextEmployeeId() throws SQLException, ClassNotFoundException;
}
