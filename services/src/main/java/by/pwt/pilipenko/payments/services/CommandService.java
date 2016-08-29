package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.BaseDAO;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.model.entities.Command;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CommandService extends AbstractEntityService<Command> {

    public List<Command> searchEntityByName(String name) throws SQLException, NamingException, ClassNotFoundException {

        Command entity = new Command();
        if (name != null && !name.equals("")) {
            entity.setCommand(name);
        }

        BaseDAO<Command> commandDAO = getEntityDAO();

        List<Command> list = getEntityDAO().findEntityByEntity(entity);
        return list;
    }

    @Override
    public BaseDAO<Command> getEntityDAO() throws SQLException, NamingException, ClassNotFoundException {
        return DaoFactoryFactory.getInstance().createCommandDAO();
    }

}
