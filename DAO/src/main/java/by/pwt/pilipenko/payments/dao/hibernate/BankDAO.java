package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.model.entities.Bank;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class BankDAO extends AbstractEntityDAO<Bank> {


    public BankDAO(Session session) {
        super(session);
    }

    @Override
    public boolean delete(Bank entity) throws SQLException, NamingException {
        Query query = getSession().createQuery("delete from Bank where unn = :unn");
        query.setParameter("unn", entity.getName());
        int result = query.executeUpdate();

        return true;
    }

    @Override
    public List<Bank> findEntityByEntity(Bank entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from Bank where unn=COALESCE(:unn, unn)");
        query.setParameter("unn", entity.getUNN());
        List<Bank> list = (List<Bank>) query.list();
        return list;
    }

    @Override
    public Bank findEntityByPK(Bank entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from Bank where unn=:unn");
        query.setParameter("unn", entity.getUNN());
        Bank bank = (Bank) query.uniqueResult();
        return bank;
    }

    @Override
    public List<Bank> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<Bank> list;

        list = (List<Bank>) getSession().createQuery("from Bank").list();
        return list;
    }

}
