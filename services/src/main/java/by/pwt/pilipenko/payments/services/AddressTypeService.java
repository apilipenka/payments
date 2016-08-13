package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.DAOFactory;
import by.pwt.pilipenko.payments.dao.TypeDAO;
import by.pwt.plipenko.payments.model.entities.Type;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;


public class AddressTypeService extends AbstractEntitySevice<Type> {

    public List<Type> searchEntityByName(String name) throws SQLException, NamingException {

        Type entity = new Type();
        if (name != null && name != "") {
            entity.setName(name);
        }

        AbstractEntityDAO<Type> addressTypeDAO = getEntityDAO();
        List<Type> list = addressTypeDAO.findEntityByEntity(entity);
        addressTypeDAO.closeConnection();
        return list;
    }

    @Override
    public AbstractEntityDAO<Type> getEntityDAO() throws NamingException, SQLException {
        TypeDAO addressTypeDAO = DAOFactory.getInstance().createAddressTypeDAO();
        return addressTypeDAO;
    }

}