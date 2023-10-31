package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.SuperDAO;
import lk.ijse.project.dao.custom.EmployeeDAO;
import lk.ijse.project.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{


    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee(eId,eName,eAddress,eJob,eSalary,eContactNo) VALUES (?,?,?,?,?,?)";
        return SQLUtil.execute(sql,entity.getEId(),entity.getEName(),entity.getEAddress(),entity.getEJob(),entity.getESalary(),entity.getEContactNo());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM employee WHERE eId=?";
        return SQLUtil.execute(sql,id);
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employee SET eName=?, eAddress=?, eContactNo=?, eJob=?, eSalary=? WHERE eId=?";
        return SQLUtil.execute(sql,entity.getEName(),entity.getEAddress(),entity.getEContactNo(),entity.getEJob(),entity.getESalary(),entity.getEId());
    }

    @Override
    public Employee searchId(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE eId=?";
        ResultSet result = SQLUtil.execute(sql, id);
        if (result.next()){
            String eId = result.getString(1);
            String eName = result.getString(2);
            String eAddress = result.getString(3);
            String  eJob = result.getString(4);
            Double eSalary =result.getDouble(5);
            String  eContact = result.getString(6);

            return new Employee(eId,eName,eAddress,eJob,eSalary,eContact);
        }
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee";

        List<Employee> data = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);

        while (resultSet.next()) {
            data.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6)
            ));
        }
        return data;
    }

    @Override
    public String genetateNextId() throws SQLException, ClassNotFoundException {
        String lastEmployeeId=generateEmployeeId();
        if(lastEmployeeId==null){
            return "E001";
        }else{
            String[] split=lastEmployeeId.split("[E]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newEmployeeId=String.format("E%03d", lastDigits);
            return newEmployeeId;
        }
    }

    private String generateEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT eId FROM employee ORDER BY eId DESC LIMIT 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
