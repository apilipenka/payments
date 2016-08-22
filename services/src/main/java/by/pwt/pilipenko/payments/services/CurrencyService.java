package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.jdbc.CurrencyDAO;
import by.pwt.plipenko.payments.model.entities.Currency;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CurrencyService extends AbstractEntitySevice<Currency> {


    public List<Currency> searchEntityByName(String name) throws SQLException, NamingException {

        Currency entity = new Currency();
        if (name != null && name != "") {
            entity.setCode(name);
            entity.setMnemoCode(name);
            entity.setName(name);
        }

        AbstractEntityDAO<Currency> currencyDAO = getEntityDAO();
        List<Currency> list = currencyDAO.findEntityByEntity(entity);
        currencyDAO.closeConnection();

        return list;

    }

    public AbstractEntityDAO<Currency> getEntityDAO() throws NamingException, SQLException {

        CurrencyDAO currencyDAO = DaoFactoryFactory.getInstance().createCurrencyDAO();
        return currencyDAO;
    }

}
