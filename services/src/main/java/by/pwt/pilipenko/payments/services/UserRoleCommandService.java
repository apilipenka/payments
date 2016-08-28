package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.plipenko.payments.model.entities.UserRole;
import by.pwt.plipenko.payments.model.entities.UserRoleCommand;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserRoleCommandService extends AbstractEntityService<UserRoleCommand> {

    public List<UserRoleCommand> searchEntityByName(String name) throws Exception {

        UserRoleCommand entity = new UserRoleCommand();
        if (name != null && !name.equals("")) {

            UserRoleService userRoleService = new UserRoleService();
            UserRole userRole1 = new UserRole();
            userRole1.setName(name);

            UserRole userRole = userRoleService.getEntityByPK(userRole1);
            if (userRole != null) {
                entity.setUserRole(userRole);
            }

        }

        BaseDAO<UserRoleCommand> userRoleCommandDAO = getEntityDAO();

        List<UserRoleCommand> list = getEntityDAO().findEntityByEntity(entity);
        return list;
    }

    @Override
    public BaseDAO<UserRoleCommand> getEntityDAO() throws SQLException, NamingException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createUserRoleCommandDAO();
    }

}
