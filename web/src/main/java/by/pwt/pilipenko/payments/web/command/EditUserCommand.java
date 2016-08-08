package by.pwt.pilipenko.payments.web.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newuser");

        UserService userService = new UserService();
        Object name = request.getAttribute("userID");

        User user = new User();

        try {
            if (name != null) {

                user = userService.getEntity(Integer.parseInt(name.toString()));

            } else {
                name = request.getParameter("userID");
                if (name != null) {
                    user = userService.getEntity(Integer.parseInt(name.toString()));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (user != null) {
            request.setAttribute("user", user.createUserVO());
        }

        request.setAttribute("command", "UPDATEUSER");
        request.setAttribute("roles", new UserRoleService().getAllEntities());
        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}