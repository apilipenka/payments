package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.plipenko.payments.model.entities.Bank;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class BankService extends AbstractEntityService<Bank> {

    public List<Bank> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {

        Bank entity = new Bank();
        if (name != null && !name.equals("")) {
            entity.setName(name);
        }

        BaseDAO<Bank> bankDAO = getEntityDAO();
        List<Bank> list = bankDAO.findEntityByEntity(entity);
        bankDAO.closeConnection();

        return list;

    }

    @Override
    public BaseDAO<Bank> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createBankDAO();
    }

}
