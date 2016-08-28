package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.services.exceptions.AccountNotFoundException;
import by.pwt.plipenko.payments.model.entities.Account;
import by.pwt.plipenko.payments.model.exceptions.InsufficientFundsException;

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

    public void getMoney(String number, double amount) throws SQLException, NamingException, ClassNotFoundException, InsufficientFundsException, AccountNotFoundException {
        Account account = new Account();
        account.setNumber(number);
        Account account1 = this.getEntityByPK(account);
        if (account1!=null) {
            account1.getMoney(amount);
            this.updateEntity(account1);
        }
        else
            throw new AccountNotFoundException("Account not found");
        
    }

    public void addMoney(String number, double amount) throws SQLException, NamingException, ClassNotFoundException, AccountNotFoundException {
        Account account = new Account();
        account.setNumber(number);
        Account account1 = this.getEntityByPK(account);
        if (account1!=null) {
            account1.addMoney(amount);
            this.updateEntity(account1);
        }
        else
            throw new AccountNotFoundException("Account not found");

    }


    public BaseDAO<Account> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createAccountDAO();
    }

}
