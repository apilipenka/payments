package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.AgreementDAO;
import by.pwt.pilipenko.payments.dao.DAOFactory;
import by.pwt.plipenko.payments.model.entities.Agreement;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class AgreementService extends AbstractEntitySevice<Agreement> {

    public List<Agreement> searchEntityByName(String name) throws SQLException, NamingException {

        Agreement entity = new Agreement();
        if (name != null && name != "") {
            entity.setNumber(name);
        }

        AbstractEntityDAO<Agreement> agreementDAO = getEntityDAO();

        List<Agreement> list = getEntityDAO().findEntityByEntity(entity);
        agreementDAO.closeConnection();
        return list;
    }

    @Override
    public AbstractEntityDAO<Agreement> getEntityDAO() throws SQLException, NamingException {
        AgreementDAO agreementDAO = DAOFactory.getInstance().createAgreementDAO();
        return agreementDAO;
    }

}