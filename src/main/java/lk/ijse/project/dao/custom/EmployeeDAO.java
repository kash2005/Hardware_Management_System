package lk.ijse.project.dao.custom;

import lk.ijse.project.dao.CrudDAO;
import lk.ijse.project.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {

    String genetateNextId() throws SQLException, ClassNotFoundException;
}
