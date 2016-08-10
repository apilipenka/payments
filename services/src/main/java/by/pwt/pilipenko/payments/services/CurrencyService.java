package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.CurrencyDAO;
import by.pwt.pilipenko.payments.dao.DAOFactory;
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

    @Override
    public AbstractEntityDAO<Currency> getEntityDAO() throws NamingException, SQLException {
        CurrencyDAO currencyDAO = DAOFactory.getInstance().createCurrencyDAO();
        return currencyDAO;
    }

}
