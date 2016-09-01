package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.model.entities.Account;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO extends AbstractEntityDAO<Account> {


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

}
