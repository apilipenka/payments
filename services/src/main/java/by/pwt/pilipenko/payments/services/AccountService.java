package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.plipenko.payments.model.entities.Account;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AccountService extends AbstractEntityService<Account> {

    public List<Account> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {
        Account entity = new Account();
        if (name != null && !name.equals("")) {
            entity.setNumber(name);
        }
        BaseDAO<Account> accountDAO = getEntityDAO();
        List<Account> list = getEntityDAO().findEntityByEntity(entity);
        accountDAO.closeConnection();
        return list;
    }

    public BaseDAO<Account> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {
       return DaoFactoryFactory.getInstance().createAccountDAO();
    }

}
