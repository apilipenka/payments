package by.pwt.pilipenko.payments.web.command.userrole;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditUserRoleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newuserrole");

        UserRoleService userRoleService = new UserRoleService();
        Object name = request.getAttribute("userRoleID");

        try {
            if (name != null) {

                request.setAttribute("userRole", userRoleService.getEntity(Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("userRoleID");
                if (name != null) {
                    request.setAttribute("userRole", userRoleService.getEntity(Integer.parseInt(name.toString())));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("command", "UPDATEUSERROLE");
        return page;
    }
}