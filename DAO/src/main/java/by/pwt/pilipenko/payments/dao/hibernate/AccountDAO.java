package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Account;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO extends AbstractEntityDAO<Account> {

    private static Logger log = Logger.getLogger(AccountDAO.class);


    public AccountDAO(Session session) {
        super(session);
    }

    @Override
    public boolean delete(Account entity) throws SQLException, NamingException {

        Query query = getSession().createQuery("delete from Account where number = :number");
        query.setParameter("number", entity.getNumber());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Account> findEntityByEntity(Account entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from Account where number=COALESCE(:number, number)");
        query.setParameter("number", entity.getNumber());
        return (List<Account>) query.list();
    }

    @Override
    public Account findEntityByPK(Account entity) throws SQLException, NamingException, ClassNotFoundException {

        Query query = getSession().createQuery("from Account where number=:number");
        query.setParameter("number", entity.getNumber());
        return (Account) query.uniqueResult();
    }

    @Override
    public List<Account> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<Account> list;

        list = (List<Account>) getSession().createQuery("from Account").list();
        return list;


    }


    public List<Account> findAllWithPagination(int page, int recordsPerPage) {
        try {
            Criteria cr = getSession().createCriteria(Account.class);
            cr.setFirstResult((page - 1) * recordsPerPage);
            cr.setMaxResults(recordsPerPage);
            List<Account> accounts = cr.list();

            if (accounts.isEmpty()) {
                if (log.isDebugEnabled()) {
                    log.debug("Admin users are not exist");
                }
                return null;
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Found " + accounts.size() + " Admin users");
                }
                return accounts;
            }
        } catch (RuntimeException ex) {
            log.error(ex);
            return null;
        }
    }

    public List<Account> findEntityByEntityWithPagination(Account entity, int page, int recordsPerPage) {
        try {
            Criteria cr = getSession().createCriteria(Account.class);
            cr.add(Restrictions.like("number", entity.getNumber()+"%"));
            cr.setFirstResult((page - 1) * recordsPerPage);
            cr.setMaxResults(recordsPerPage);
            List<Account> accounts = cr.list();

            if (accounts.isEmpty()) {
                if (log.isDebugEnabled()) {
                    log.debug("Admin users are not exist");
                }
                return null;
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Found " + accounts.size() + " Admin users");
                }
                return accounts;
            }
        } catch (RuntimeException ex) {
            log.error(ex);
            return null;
        }
    }


}
