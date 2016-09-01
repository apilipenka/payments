package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.model.entities.Currency;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CurrencyDAO extends AbstractEntityDAO<Currency> {

    public CurrencyDAO(Session session) {
        super(session);
    }

    @Override
    public boolean delete(Currency entity) throws SQLException, NamingException {

        Query query = getSession().createQuery("delete from Currency where mnemoCode = :mnemoCode");
        query.setParameter("mnemoCode", entity.getMnemoCode());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Currency> findEntityByEntity(Currency entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from Currency where mnemoCode = :mnemoCode");
        query.setParameter("mnemoCode", entity.getMnemoCode());
        return (List<Currency>) query.list();
    }

    @Override
    public Currency findEntityByPK(Currency entity) throws SQLException, NamingException, ClassNotFoundException {

        Query query = getSession().createQuery("from Currency where mnemoCode = :mnemoCode");
        query.setParameter("mnemoCode", entity.getMnemoCode());
        return (Currency) query.uniqueResult();
    }

    @Override
    public List<Currency> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<Currency> list;

        list = (List<Currency>) getSession().createQuery("from Currency").list();
        return list;


    }


}
