package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.DAOFactory;
import by.pwt.pilipenko.payments.dao.ExchangeRateDAO;
import by.pwt.plipenko.payments.model.entities.Currency;
import by.pwt.plipenko.payments.model.entities.ExchangeRate;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExchangeRateService extends AbstractEntitySevice<ExchangeRate> {

    public List<ExchangeRate> searchEntityByName(String name) throws SQLException, NamingException {

        ExchangeRate entity = new ExchangeRate();
        if (name != null && name != "") {

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            try {
                entity.setRateDate(format.parse(name));
            } catch (ParseException e) {
                //Name may contain not date
                ;
            }


            CurrencyService currencyService = new CurrencyService();
            Currency currency = new Currency();
            currency.setMnemoCode(name);
            Currency currency1 = currencyService.getEntityByPK(currency);
            if (currency1 != null) {
                entity.setCurrency(currency1);
            }


        }


        AbstractEntityDAO<ExchangeRate> exchangeRateDAO = getEntityDAO();
        List<ExchangeRate> list = exchangeRateDAO.findEntityByEntity(entity);
        exchangeRateDAO.closeConnection();

        return list;

    }

    public List<ExchangeRate> searchEntityParent(Integer currencyId, Integer targetCurrencyId) throws Exception {

        ExchangeRate entity = new ExchangeRate();
        if (currencyId != null) {

            CurrencyService currencyService = new CurrencyService();
            Currency currency = currencyService.getEntity(currencyId);
            if (currency != null) {
                entity.setCurrency(currency);
            }


        }

        if (targetCurrencyId != null) {

            CurrencyService currencyService = new CurrencyService();
            Currency currency = currencyService.getEntity(targetCurrencyId);
            if (currency != null) {
                entity.setTargetCurrency(currency);
            }


        }


        AbstractEntityDAO<ExchangeRate> exchangeRateDAO = getEntityDAO();


        List<ExchangeRate> list = ((ExchangeRateDAO) exchangeRateDAO).findEntityByEntity(entity);
        exchangeRateDAO.closeConnection();

        return list;

    }


    @Override
    public AbstractEntityDAO<ExchangeRate> getEntityDAO() throws NamingException, SQLException {
        ExchangeRateDAO exchangeRateDAO = DAOFactory.getInstance().createExchangeRateDAO();
        return exchangeRateDAO;
    }

}
