package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.plipenko.payments.model.entities.Card;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CardService extends AbstractEntityService<Card> {

    public List<Card> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {

        Card entity = new Card();
        if (name != null && !name.equals("")) {
            entity.setNumber(name);
        }

        BaseDAO<Card> cardDAO = getEntityDAO();

        List<Card> list = getEntityDAO().findEntityByEntity(entity);
        return list;
    }

    @Override
    public BaseDAO<Card> getEntityDAO() throws SQLException, NamingException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createCardDAO();
    }

}
