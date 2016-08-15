package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.CardDAO;
import by.pwt.pilipenko.payments.dao.DAOFactory;
import by.pwt.plipenko.payments.model.entities.Card;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CardService extends AbstractEntitySevice<Card> {

    public List<Card> searchEntityByName(String name) throws SQLException, NamingException {

        Card entity = new Card();
        if (name != null && name != "") {
            entity.setNumber(name);
        }

        AbstractEntityDAO<Card> cardDAO = getEntityDAO();

        List<Card> list = getEntityDAO().findEntityByEntity(entity);
        cardDAO.closeConnection();
        return list;
    }

    @Override
    public AbstractEntityDAO<Card> getEntityDAO() throws SQLException, NamingException {
        CardDAO cardDAO = DAOFactory.getInstance().createCardDAO();
        return cardDAO;
    }

}
