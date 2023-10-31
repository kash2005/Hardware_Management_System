package lk.ijse.project.bo.custom.impl;

import lk.ijse.project.bo.custom.ItemBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.ItemDAO;
import lk.ijse.project.dto.CustomerDTO;
import lk.ijse.project.dto.ItemDTO;
import lk.ijse.project.entity.Customer;
import lk.ijse.project.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(itemDTO.getItemCode(),itemDTO.getName(),itemDTO.getItemType(),itemDTO.getSellPrice(),itemDTO.getGetPrice(),itemDTO.getQuantity()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getItemCode(),itemDTO.getName(),itemDTO.getItemType(),itemDTO.getSellPrice(),itemDTO.getGetPrice(),itemDTO.getQuantity()));
    }

    @Override
    public boolean deleteItem(String item_code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(item_code);
    }

    @Override
    public ItemDTO searchItem(String item_code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.searchId(item_code);
        return new ItemDTO(item.getItemCode(),item.getName(),item.getItemType(),item.getSellPrice(),item.getGetPrice(),item.getQuantity());
    }

    @Override
    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        List<ItemDTO> allItems = new ArrayList<>();
        List<Item> all = itemDAO.getAll();
        for (Item item:all) {
            allItems.add(new ItemDTO(item.getItemCode(),item.getName(),item.getItemType(),item.getSellPrice(),item.getGetPrice(),item.getQuantity()));
        }
        return allItems;
    }

    @Override
    public String generateNextItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNextId();
    }


}
