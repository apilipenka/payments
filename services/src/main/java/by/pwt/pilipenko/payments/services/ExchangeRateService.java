package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.BaseExchangeRateDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Currency;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExchangeRateService extends AbstractEntityService<ExchangeRate> {

    public List<ExchangeRate> searchEntityByName(String name) throws Exception {

        ExchangeRate entity = new ExchangeRate();
        if (name != null && !name.equals("")) {

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            try {
                entity.setRateDate(format.parse(name));
            } catch (ParseException e) {
                //Name may contain not date

            }


            CurrencyService currencyService = new CurrencyService();
            Currency currency = new Currency();
            currency.setMnemoCode(name);
            Currency currency1 = currencyService.getEntityByPK(currency);
            if (currency1 != null) {
                entity.setCurrency(currency1);
                entity.setTargetCurrency(currency1);
            }
        }
        BaseDAO<ExchangeRate> exchangeRateDAO = getEntityDAO();
        List<ExchangeRate> list = exchangeRateDAO.findEntityByEntity(entity);

        return list;

    }

    public List<ExchangeRate> searchEntityParent(Integer currencyId, Integer targetCurrencyId) throws Exception {

        ExchangeRate entity = new ExchangeRate();
        CurrencyService currencyService = new CurrencyService();


        if (currencyId != null) {
            Currency currency = currencyService.getEntity(currencyId);
            if (currency != null) {
                entity.setCurrency(currency);
            }
        }

        if (targetCurrencyId != null) {
            Currency currency = currencyService.getEntity(targetCurrencyId);
            if (currency != null) {
                entity.setTargetCurrency(currency);
            }
        }


        BaseDAO<ExchangeRate> exchangeRateDAO = getEntityDAO();
        List<ExchangeRate> list = ((BaseExchangeRateDAO) exchangeRateDAO).findEntityByParent(entity);

        return list;

    }


    @Override
    public BaseDAO<ExchangeRate> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createExchangeRateDAO();
    }

}
