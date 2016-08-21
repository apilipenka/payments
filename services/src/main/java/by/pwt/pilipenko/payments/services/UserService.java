package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.UserDAO;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserService extends AbstractEntitySevice<User> {

    public List<User> searchEntityByName(String name) throws SQLException, NamingException {

        User entity = new User();
        if (name != null && name != "") {
            entity.setLogin(name);
            entity.setFirstName(name);
            entity.setLastName(name);
            entity.setPersonalNumber(name);
        }

        AbstractEntityDAO<User> userDAO = getEntityDAO();

        List<User> list = getEntityDAO().findEntityByEntity(entity);
        userDAO.closeConnection();
        return list;
    }

    @Override
    public AbstractEntityDAO<User> getEntityDAO() throws SQLException, NamingException {
        UserDAO userDAO = DaoFactoryFactory.getInstance().createUserDAO();
        return userDAO;
    }

}
