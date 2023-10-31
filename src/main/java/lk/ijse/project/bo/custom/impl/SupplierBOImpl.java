package lk.ijse.project.bo.custom.impl;

import lk.ijse.project.bo.SuperBO;
import lk.ijse.project.bo.custom.SupplierBO;
import lk.ijse.project.dao.DAOFactory;
import lk.ijse.project.dao.custom.SupplierDAO;
import lk.ijse.project.dto.SupplierDTO;
import lk.ijse.project.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO{

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getCompany(),supplierDTO.getContactNo()));
    }

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getCompany(),supplierDTO.getContactNo()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public List<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        List<SupplierDTO> allSuppliers= new ArrayList<>();
        List<Supplier> all = supplierDAO.getAll();
        for (Supplier supplier:all) {
            allSuppliers.add(new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getCompany(),supplier.getContactNo()));
        }
        return allSuppliers;
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchId(id);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getCompany(),supplier.getContactNo());
    }
}
