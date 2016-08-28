package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.services.exceptions.AccountNotFoundException;
import by.pwt.plipenko.payments.model.entities.Account;
import by.pwt.plipenko.payments.model.exceptions.InsufficientFundsException;
import by.pwt.plipenko.payments.model.exceptions.RateNotFoundException;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AccountService extends AbstractEntityService<Account> {

    public List<Account> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {
        Account entity = new Account();
        if (name != null && !name.equals("")) {
            entity.setNumber(name);
        }
        BaseDAO<Account> accountDAO = getEntityDAO();
        List<Account> list = getEntityDAO().findEntityByEntity(entity);
        return list;
    }

    public void getMoney(String accountNumber, double amount) throws Exception {
        Account account = new Account();
        account.setNumber(accountNumber);
        Account account1 = this.getEntityByPK(account);
        if (account1!=null) {
            account1.getMoney(amount);
            this.updateEntity(account1);
        }
        else
            throw new AccountNotFoundException("Account "+accountNumber+" not found");

    }

    public void addMoney(String accountNumber, double amount) throws Exception {
        Account account = new Account();
        account.setNumber(accountNumber);
        Account account1 = this.getEntityByPK(account);
        if (account1!=null) {
            account1.addMoney(amount);
            this.updateEntity(account1);
        }
        else
            throw new AccountNotFoundException("Account "+accountNumber+" not found");

    }

    public void transferMoney(String creditAccountNumber, String debitAccountNumber, double amount) throws Exception {
        Account account = new Account();
        account.setNumber(creditAccountNumber);
        Account creditAccount = this.getEntityByPK(account);
        if (creditAccount==null)
            throw new AccountNotFoundException("Account "+creditAccountNumber+" not found");
        account.setNumber(debitAccountNumber);
        Account debitAccount = this.getEntityByPK(account);
        if (debitAccount==null)
            throw new AccountNotFoundException("Account "+debitAccountNumber+" not found");
        try {
            DaoFactoryFactory.getInstance().beginTransaction();
            creditAccount.getMoney(amount);
            this.updateEntity(creditAccount);
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            debitAccount.addMoney(debitAccount.getCurrency().getRate(format.parse(format.format(Calendar.getInstance().getTime())),creditAccount.getCurrency())*amount);
            this.updateEntity(debitAccount);
            DaoFactoryFactory.getInstance().commit();
            DaoFactoryFactory.getInstance().endTransaction();
        } catch (Exception e) {
            DaoFactoryFactory.getInstance().rollback();
            throw e;
        }

    }


    public BaseDAO<Account> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createAccountDAO();
    }

}
