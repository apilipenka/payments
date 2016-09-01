package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.model.entities.Agreement;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AgreementDAO extends AbstractEntityDAO<Agreement> {


    public AgreementDAO(Session session) {
        super(session);
    }

    @Override
    public boolean delete(Agreement entity) throws SQLException, NamingException {

        Query query = getSession().createQuery("delete from Agreement where number = :number");
        query.setParameter("number", entity.getNumber());
        int result = query.executeUpdate();

        return true;
    }


    @Override
    public List<Agreement> findEntityByEntity(Agreement entity) throws SQLException, NamingException, ClassNotFoundException {
        Query query = getSession().createQuery("from Agreement where number=COALESCE(:number, number)");
        query.setParameter("number", entity.getNumber());
        return (List<Agreement>) query.list();
    }

    @Override
    public Agreement findEntityByPK(Agreement entity) throws SQLException, NamingException, ClassNotFoundException {

        Query query = getSession().createQuery("from Agreement where number=:number");
        query.setParameter("number", entity.getNumber());
        return (Agreement) query.uniqueResult();
    }

    @Override
    public List<Agreement> findAll() throws SQLException, NamingException, ClassNotFoundException {
        List<Agreement> list;

        list = (List<Agreement>) getSession().createQuery("from Agreement").list();
        return list;


    }

}
