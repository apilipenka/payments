package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Bank;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class BankService extends AbstractEntityService<Bank> {

    public List<Bank> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {
        boolean flag = DaoFactoryFactory.getInstance().isParentTransactionStarted();
        try {
            if (!flag)
                DaoFactoryFactory.getInstance().beginTransaction();
            Bank entity = new Bank();
            if (name != null && !name.equals("")) {
                entity.setName(name);
            }

            BaseDAO<Bank> bankDAO = getEntityDAO();

            return bankDAO.findEntityByEntity(entity);
        } finally {
            if (!flag)
                DaoFactoryFactory.getInstance().commit();
        }
    }

    @Override
    public BaseDAO<Bank> getEntityDAO() throws NamingException, SQLException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createBankDAO();
    }

}
