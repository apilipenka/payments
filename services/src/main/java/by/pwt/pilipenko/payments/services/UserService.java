package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.UserDAO;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserService extends AbstractEntityService<User> {

    public List<User> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {

        User entity = new User();
        if (name != null && !name.equals("")) {
            entity.setLogin(name);
            entity.setFirstName(name);
            entity.setLastName(name);
            entity.setPersonalNumber(name);
        }

        BaseDAO<User> userDAO = getEntityDAO();

        List<User> list = getEntityDAO().findEntityByEntity(entity);
        userDAO.closeConnection();
        return list;
    }

    @Override
    public BaseDAO<User> getEntityDAO() throws SQLException, NamingException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createUserDAO();
    }

}
