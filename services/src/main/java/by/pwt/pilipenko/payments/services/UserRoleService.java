package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.DAOFactory;
import by.pwt.pilipenko.payments.dao.UserRoleDAO;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserRoleService extends AbstractEntitySevice<UserRole> {

    public List<UserRole> searchEntityByName(String name) throws SQLException, NamingException {

        UserRole entity = new UserRole();
        if (name != null && name != "") {
            entity.setName(name);
        }

        AbstractEntityDAO<UserRole> userRoleTypeDAO = getEntityDAO();
        List<UserRole> list = userRoleTypeDAO.findEntityByEntity(entity);
        userRoleTypeDAO.closeConnection();

        return list;

    }

    @Override
    public AbstractEntityDAO<UserRole> getEntityDAO() throws NamingException, SQLException {
        UserRoleDAO userRoleDAO = DAOFactory.getInstance().createUserRoleDAO();
        return userRoleDAO;
    }

}
