package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Currency;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CurrencyService extends AbstractEntityService<Currency> {


    public List<Currency> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {

        Currency entity = new Currency();
        if (name != null && !name.equals("")) {
            entity.setCode(name);
            entity.setMnemoCode(name);
            entity.setName(name);
        }

        BaseDAO<Currency> currencyDAO = getEntityDAO();
        List<Currency> list = currencyDAO.findEntityByEntity(entity);

        return list;

    }

    public BaseDAO<Currency> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {

        return DaoFactoryFactory.getInstance().createCurrencyDAO();
    }

}
