package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.AccountDAO;
import by.pwt.pilipenko.payments.dao.DAOFactory;
import by.pwt.plipenko.payments.model.entities.Account;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AccountService extends AbstractEntitySevice<Account> {

    public List<Account> searchEntityByName(String name) throws SQLException, NamingException {

        Account entity = new Account();
        if (name != null && name != "") {
            entity.setNumber(name);
        }

        AbstractEntityDAO<Account> accountDAO = getEntityDAO();

        List<Account> list = getEntityDAO().findEntityByEntity(entity);
        accountDAO.closeConnection();
        return list;
    }

    @Override
    public AbstractEntityDAO<Account> getEntityDAO() throws SQLException, NamingException {
        AccountDAO accountDAO = DAOFactory.getInstance().createAccountDAO();
        return accountDAO;
    }

}
