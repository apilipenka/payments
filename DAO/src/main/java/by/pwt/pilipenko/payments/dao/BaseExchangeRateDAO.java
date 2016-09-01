package by.pwt.pilipenko.payments.dao;

import by.pwt.pilipenko.payments.model.entities.AbstractEntity;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by apilipenka on 9/1/2016.
 */
public interface BaseExchangeRateDAO<E extends AbstractEntity>   {
    List<ExchangeRate> findEntityByParent(ExchangeRate entity) throws SQLException, NamingException, ClassNotFoundException;

}
