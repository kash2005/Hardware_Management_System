package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.dto.UserDTO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean checkPaswordAndUserName(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
