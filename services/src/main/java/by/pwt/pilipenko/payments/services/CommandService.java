package by.pwt.pilipenko.payments.services;

import by.pwt.pilipenko.payments.dao.AbstractEntityDAO;
import by.pwt.pilipenko.payments.dao.CommandDAO;
import by.pwt.pilipenko.payments.dao.DAOFactory;
import by.pwt.plipenko.payments.model.entities.Command;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class CommandService extends AbstractEntitySevice<Command> {

    public List<Command> searchEntityByName(String name) throws SQLException, NamingException {

        Command entity = new Command();
        if (name != null && name != "") {
            entity.setCommand(name);
        }

        AbstractEntityDAO<Command> commandDAO = getEntityDAO();

        List<Command> list = getEntityDAO().findEntityByEntity(entity);
        commandDAO.closeConnection();
        return list;
    }

    @Override
    public AbstractEntityDAO<Command> getEntityDAO() throws SQLException, NamingException {
        CommandDAO commandDAO = DAOFactory.getInstance().createCommandDAO();
        return commandDAO;
    }

}
