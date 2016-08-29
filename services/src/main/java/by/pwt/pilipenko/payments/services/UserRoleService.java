package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.UserRole;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserRoleService extends AbstractEntityService<UserRole> {

    public List<UserRole> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {

        UserRole entity = new UserRole();
        if (name != null && !name.equals("")) {
            entity.setName(name);
        }

        BaseDAO<UserRole> userRoleTypeDAO = getEntityDAO();
        List<UserRole> list = userRoleTypeDAO.findEntityByEntity(entity);

        return list;

    }

    @Override
    public BaseDAO<UserRole> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createUserRoleDAO();
    }

}
