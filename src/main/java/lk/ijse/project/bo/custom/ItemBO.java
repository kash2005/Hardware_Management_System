package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String item_code) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String item_code) throws SQLException, ClassNotFoundException;

    List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    String generateNextItemId() throws SQLException, ClassNotFoundException;
}
