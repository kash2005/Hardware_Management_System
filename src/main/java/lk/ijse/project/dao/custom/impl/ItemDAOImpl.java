package lk.ijse.project.dao.custom.impl;

import lk.ijse.project.dao.SQLUtil;
import lk.ijse.project.dao.custom.ItemDAO;
import lk.ijse.project.entity.Item;
import lk.ijse.project.entity.SupplyOrder;
import lk.ijse.project.entity.SupplyOrderDetails;
//import org.fxconnector.details.Detail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item() VALUES(?,?,?,?,?,?) ";
        return SQLUtil.execute(sql,entity.getItemCode(),entity.getName(),entity.getItemType(),entity.getSellPrice(),entity.getGetPrice(),entity.getQuantity());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM item WHERE itemCode=?";
        return  SQLUtil.execute(sql,id);
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET name = ? , itemType = ?, sell_price = ?, get_price = ?, quantity = ? WHERE itemCode = ?";
        return SQLUtil.execute(sql,entity.getItemCode(),entity.getName(),entity.getItemType(),entity.getSellPrice(),entity.getGetPrice(),entity.getQuantity());
    }

    @Override
    public Item searchId(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item WHERE itemCode=?";
        ResultSet result = SQLUtil.execute(sql, id);
        if (result.next()){
            String code = result.getString(1);
            String name = result.getString(2);
            String type = result.getString(3);
            Double sellPrice =result.getDouble(4);
            Double getPrice = result.getDouble(5);
            int qty = result.getInt(6);

            return new Item(code,name,type,sellPrice,getPrice,qty);
        }
        return null;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM item";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<Item> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6)
            ));
        }
        return data;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        String lastItemId=generateItemId();
        if(lastItemId==null){
            return "I001";
        }else{
            String[] split=lastItemId.split("[I]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newItemId=String.format("I%03d", lastDigits);
            return newItemId;
        }
    }

    @Override
    public boolean updateLoadQty(SupplyOrderDetails details) throws SQLException, ClassNotFoundException {
        String sql ="UPDATE item SET quantity = quantity + ? WHERE itemCode =?";
        return SQLUtil.execute(sql,details.getQty(),details.getCode());
    }

    private String generateItemId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT itemCode FROM item ORDER BY itemCode DESC LIMIT 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
