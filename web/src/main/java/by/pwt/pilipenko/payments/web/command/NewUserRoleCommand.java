package by.pwt.pilipenko.payments.web.command;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class NewUserRoleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newuserrole");

        return page;
    }
}