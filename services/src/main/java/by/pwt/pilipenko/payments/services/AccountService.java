package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.hibernate.AccountDAO;
import by.pwt.pilipenko.payments.model.entities.Account;
import by.pwt.pilipenko.payments.services.exceptions.AccountNotFoundException;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AccountService extends AbstractEntityService<Account> {

    public List<Account> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            Account entity = new Account();
            if (name != null && !name.equals("")) {
                entity.setNumber(name);
            }
            return getEntityDAO().findEntityByEntity(entity);
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }

    public void getMoney(String accountNumber, double amount) throws Exception {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            Account account = new Account();
            account.setNumber(accountNumber);
            Account account1 = this.getEntityByPK(account);
            if (account1 != null) {
                account1.getMoney(amount);
                this.updateEntity(account1);
            } else
                throw new AccountNotFoundException("Account " + accountNumber + " not found");
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        } catch (Exception e) {
            if (!flag)
                DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
    }

    public void addMoney(String accountNumber, double amount) throws Exception {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            Account account = new Account();
            account.setNumber(accountNumber);
            Account account1 = this.getEntityByPK(account);
            if (account1 != null) {
                account1.addMoney(amount);
                this.updateEntity(account1);
            } else
                throw new AccountNotFoundException("Account " + accountNumber + " not found");
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        } catch (Exception e) {
            if (!flag)
                DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
    }

    public void transferMoney(String creditAccountNumber, String debitAccountNumber, double amount) throws Exception {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            Account account = new Account();
            account.setNumber(creditAccountNumber);
            Account creditAccount = this.getEntityByPK(account);
            if (creditAccount == null)
                throw new AccountNotFoundException("Account " + creditAccountNumber + " not found");
            account.setNumber(debitAccountNumber);
            Account debitAccount = this.getEntityByPK(account);
            if (debitAccount == null)
                throw new AccountNotFoundException("Account " + debitAccountNumber + " not found");

            creditAccount.getMoney(amount);
            this.updateEntity(creditAccount);
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            debitAccount.addMoney(debitAccount.getCurrency().getRate(format.parse(format.format(Calendar.getInstance().getTime())), creditAccount.getCurrency()) * amount);
            this.updateEntity(debitAccount);
            if (!flag) {
                DaoFactoryFactory.getInstance().commit();
                DaoFactoryFactory.getInstance().endTransaction();
            }
        } catch (Exception e) {
            if (!flag)
                DaoFactoryFactory.getInstance().rollback();
            throw e;
        }
    }


    public BaseDAO<Account> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createAccountDAO();
    }


    public List<Account> searchEntityByNameWithPagination(String name, int page, int recordsPerPage) throws SQLException, NamingException, ClassNotFoundException {

        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            Account entity = new Account();
            if (name != null && !name.equals("")) {
                entity.setNumber(name);
            }
            return ((AccountDAO) getEntityDAO()).findEntityByEntityWithPagination(entity, page, recordsPerPage);
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }

    public List<Account> getAllEntitiesWithPagination(int page, int recordsPerPage) throws SQLException, NamingException, ClassNotFoundException {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            return ((AccountDAO) getEntityDAO()).findAllWithPagination(page, recordsPerPage);
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }

    public long getCountEntityByNameWithPagination(String name) throws SQLException, NamingException, ClassNotFoundException {

        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            Account entity = new Account();
            if (name != null && !name.equals("")) {
                entity.setNumber(name);
            }
            return ((AccountDAO) getEntityDAO()).getRecordsCountEntityByEntity(entity);
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }

    public long getCountAllEntitiesWithPagination() throws SQLException, NamingException, ClassNotFoundException {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            return ((AccountDAO) getEntityDAO()).getAllRecordsCount();
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }


}
