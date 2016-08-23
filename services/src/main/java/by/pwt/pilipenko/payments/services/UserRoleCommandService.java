package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.UserRoleCommandDAO;
import by.pwt.plipenko.payments.model.entities.UserRole;
import by.pwt.plipenko.payments.model.entities.UserRoleCommand;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserRoleCommandService extends AbstractEntitySevice<UserRoleCommand> {

    public List<UserRoleCommand> searchEntityByName(String name) throws Exception {

        UserRoleCommand entity = new UserRoleCommand();
        if (name != null && name != "") {

            UserRoleService userRoleService = new UserRoleService();
            UserRole userRole1 = new UserRole();
            userRole1.setName(name);

            UserRole userRole = userRoleService.getEntityByPK(userRole1);
            if (userRole != null) {
                entity.setUserRole(userRole);
            }

        }

        AbstractEntityDAO<UserRoleCommand> userRoleCommandDAO = getEntityDAO();

        List<UserRoleCommand> list = getEntityDAO().findEntityByEntity(entity);
        userRoleCommandDAO.closeConnection();
        return list;
    }

    @Override
    public AbstractEntityDAO<UserRoleCommand> getEntityDAO() throws SQLException, NamingException, ClassNotFoundException {
        UserRoleCommandDAO userRoleCommandDAO = DaoFactoryFactory.getInstance().createUserRoleCommandDAO();
        return userRoleCommandDAO;
    }

}
