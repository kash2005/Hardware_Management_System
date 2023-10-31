package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.UserDAO;
import lk.ijse.project.dto.UserDTO;
import lk.ijse.project.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User searchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean check(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT password,userName FROM user");

        while (resultSet.next()){
            String passwordDb=resultSet.getString(1);
            if ((resultSet.getString(2).equals(userDTO.getUserName()))&&(passwordDb.equals(userDTO.getPassword()) || passwordDb.equals(userDTO.getPassword1()))){
                return true;
            }
        }

        return false;
    }
}
