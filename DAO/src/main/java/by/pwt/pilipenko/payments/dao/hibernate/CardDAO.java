package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.model.entities.Card;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CardDAO extends AbstractEntityDAO<Card> {


    public CardDAO(Session session) {
        super(session);
    }

    @Override
    public boolean delete(Card entity) throws SQLException, NamingException {

        Query query = getSession().createQuery("delete from Card where number = :number");
        query.setParameter("number", entity.getNumber());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Card> findEntityByEntity(Card entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from Card where number=COALESCE(:number, number)");
        query.setParameter("number", entity.getNumber());
        return (List<Card>) query.list();
    }

    @Override
    public Card findEntityByPK(Card entity) throws SQLException, NamingException, ClassNotFoundException {

        Query query = getSession().createQuery("from Card where number=:number");
        query.setParameter("number", entity.getNumber());
        return (Card) query.uniqueResult();
    }

    @Override
    public List<Card> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<Card> list;

        list = (List<Card>) getSession().createQuery("from Card").list();
        return list;


    }

}
