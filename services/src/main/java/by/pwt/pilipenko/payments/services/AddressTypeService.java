package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.TypeDAO;
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

    public AbstractEntityDAO<Type> getEntityDAO() throws NamingException, SQLException {

        TypeDAO addressDAO = DaoFactoryFactory.getInstance().createAddressTypeDAO();
        return addressDAO;
    }

}
