package lk.ijse.project.bo.custom.impl;

import lk.ijse.project.bo.custom.UserBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.UserDAO;
import lk.ijse.project.dto.UserDTO;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public boolean checkPaswordAndUserName(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.check(new UserDTO(userDTO.getUserName(),userDTO.getPassword(), userDTO.getPassword1()));
    }
}
