package lk.ijse.project.dao.custom;

import lk.ijse.project.dao.CrudDAO;
import lk.ijse.project.dto.UserDTO;
import lk.ijse.project.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    boolean check(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
