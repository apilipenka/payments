package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.plipenko.payments.model.entities.Agreement;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AgreementService extends AbstractEntityService<Agreement> {

    public List<Agreement> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {
        Agreement entity = new Agreement();
        if (name != null && !name.equals("")) {
            entity.setNumber(name);
        }
        BaseDAO<Agreement> agreementDAO = getEntityDAO();
        List<Agreement> list = getEntityDAO().findEntityByEntity(entity);
        agreementDAO.closeConnection();
        return list;
    }

    public BaseDAO<Agreement> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createAgreementDAO();
    }

}
