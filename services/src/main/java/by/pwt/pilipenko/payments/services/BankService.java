package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.BankDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.JDBCDAOFactory;
import by.pwt.plipenko.payments.model.entities.Bank;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class BankService extends AbstractEntitySevice<Bank> {

    public List<Bank> searchEntityByName(String name) throws SQLException, NamingException {

        Bank entity = new Bank();
        if (name != null && name != "") {
            entity.setName(name);
        }

        AbstractEntityDAO<Bank> bankDAO = getEntityDAO();
        List<Bank> list = bankDAO.findEntityByEntity(entity);
        bankDAO.closeConnection();

        return list;

    }

    @Override
    public AbstractEntityDAO<Bank> getEntityDAO() throws NamingException, SQLException {
        BankDAO bankDAO = DaoFactoryFactory.getInstance().createBankDAO();
        return bankDAO;
    }

}
