package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.model.entities.ExchangeRate;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class ExchangeRateDAO extends AbstractEntityDAO<ExchangeRate> {

    public ExchangeRateDAO(Session session) {
        super(session);
    }

    @Override
    public boolean delete(ExchangeRate entity) throws SQLException, NamingException {

        Query query = getSession().createQuery("delete from ExchangeRate where currency = :currency and targetCurrency = :targetCurrency");
        query.setParameter("currency", entity.getCurrency());
        query.setParameter("targetCurrency", entity.getTargetCurrency());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<ExchangeRate> findEntityByEntity(ExchangeRate entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from ExchangeRate where rateDate=COALESCE(:rateDate, rateDate)");
        query.setParameter("rateDate", entity.getRateDate());
        List<ExchangeRate> list = (List<ExchangeRate>) query.list();
        return list;
    }

    @Override
    public ExchangeRate findEntityByPK(ExchangeRate entity) throws SQLException, NamingException, ClassNotFoundException {

        Query query = getSession().createQuery("from ExchangeRate where currency = :currency and targetCurrency = :targetCurrency and rateDate=:rateDate");
        query.setParameter("currency", entity.getCurrency());
        query.setParameter("targetCurrency", entity.getTargetCurrency());
        query.setParameter("rateDate", entity.getRateDate());
        ExchangeRate command = (ExchangeRate) query.uniqueResult();
        return command;
    }

    @Override
    public List<ExchangeRate> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<ExchangeRate> list;

        list = (List<ExchangeRate>) getSession().createQuery("from ExchangeRate").list();
        return list;


    }


}
