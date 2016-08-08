package by.pwt.pilipenko.payments.web.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleService;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UserRoleListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.userrolelist");

        UserRoleService userRoleService = new UserRoleService();
        Object name = request.getAttribute("userRoleName");

        if (name != null) {
            request.setAttribute("userRoleList", userRoleService.searchEntityByName(name.toString()));
        } else {
            name = request.getParameter("userRoleName");
            if (name != null) {
                request.setAttribute("userRoleList", userRoleService.searchEntityByName(name.toString()));
            } else {
                request.setAttribute("userRoleList", userRoleService.getAllEntities());
            }
        }
        return page;
    }
}