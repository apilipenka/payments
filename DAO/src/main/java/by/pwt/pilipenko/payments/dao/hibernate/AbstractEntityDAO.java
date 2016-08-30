package by.pwt.pilipenko.payments.dao.hibernate;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.model.entities.AbstractEntity;
import org.hibernate.Session;

/**
 * Created by Darrko on 30.08.2016.
 */
public abstract class AbstractEntityDAO<T extends AbstractEntity> implements BaseDAO<T> {
    private Session session;

    public AbstractEntityDAO(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


}
