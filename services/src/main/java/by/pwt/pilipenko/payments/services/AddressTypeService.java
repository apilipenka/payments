package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.plipenko.payments.model.entities.Type;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;


public class AddressTypeService extends AbstractEntityService<Type> {


    public List<Type> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {

        Type entity = new Type();
        if (name != null && !name.equals("")) {
            entity.setName(name);
        }

        BaseDAO<Type> addressTypeDAO = getEntityDAO();
        List<Type> list = addressTypeDAO.findEntityByEntity(entity);
        addressTypeDAO.closeConnection();
        return list;
    }

    public BaseDAO<Type> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {

        return DaoFactoryFactory.getInstance().createAddressTypeDAO();
    }

}
