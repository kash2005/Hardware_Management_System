package lk.ijse.project.bo.custom;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

public interface SupplierBO extends SuperBO{
    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    List<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;
}
