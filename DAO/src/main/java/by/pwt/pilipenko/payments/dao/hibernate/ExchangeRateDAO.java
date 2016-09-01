package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseExchangeRateDAO;
import by.pwt.pilipenko.payments.model.entities.Currency;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class ExchangeRateDAO extends AbstractEntityDAO<ExchangeRate> implements BaseExchangeRateDAO<ExchangeRate> {

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
        return (List<ExchangeRate>) query.list();
    }

    @Override
    public ExchangeRate findEntityByPK(ExchangeRate entity) throws SQLException, NamingException, ClassNotFoundException {

        Query query = getSession().createQuery("from ExchangeRate where currency = :currency and targetCurrency = :targetCurrency and rateDate=:rateDate");
        query.setParameter("currency", entity.getCurrency());
        query.setParameter("targetCurrency", entity.getTargetCurrency());
        query.setParameter("rateDate", entity.getRateDate());
        return (ExchangeRate) query.uniqueResult();
    }

    @Override
    public List<ExchangeRate> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<ExchangeRate> list;

        list = (List<ExchangeRate>) getSession().createQuery("from ExchangeRate").list();
        return list;


    }


    @Override
    public List<ExchangeRate> findEntityByParent(ExchangeRate entity) throws SQLException, NamingException, ClassNotFoundException {
        //currency_id=ifnull(?,currency_id) and target_currency_id=ifnull(?,target_currency_id)

        Query query = getSession().createQuery("from ExchangeRate where currency=COALESCE(:currency, currency) and  targetCurrency=COALESCE(:targetCurrency, targetCurrency)");
        query.setParameter("currency", entity.getCurrency());
        query.setParameter("targetCurrency", entity.getTargetCurrency());
        return (List<ExchangeRate>) query.list();
    }
}
