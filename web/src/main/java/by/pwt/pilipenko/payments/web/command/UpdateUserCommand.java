package by.pwt.pilipenko.payments.web.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.entities.User;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String error = null;
        User user = null;
        String page = null;
        try {
            String id = request.getParameter("id");
            String login = request.getParameter("login");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String personalNumber = request.getParameter("personalNumber");
            String role = request.getParameter("role");

            UserRoleService userRoleService = new UserRoleService();

            UserRole userRole = null;
            try {
                userRole = userRoleService.getEntity(Integer.parseInt(role));
            } catch (Exception e) {
                throw e;
            }

            String birthDateStr = request.getParameter("birthDate");
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate = format.parse(birthDateStr);

            user = new User();
            user.setId(Integer.parseInt(id));
            user.setLogin(login);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setPersonalNumber(personalNumber);
            user.setUserRole(userRole);
            user.setBirthDate(birthDate);

            UserService userService = new UserService();
            boolean flag = userService.updateEntity(user);
            if (flag) {
                request.getSession().setAttribute("success", "true");

                page = ConfigurationManager.getProperty("path.page.userlist");

                request.getSession().setAttribute("message", "The user has been successfully updated.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("user", user.createUserVO());

            try {
                request.setAttribute("roles", new UserRoleService().getAllEntities());
            } catch (NamingException e1) {
                error += "<br/>" + e1.getMessage();

            } catch (SQLException e1) {
                error += "<br/>" + e1.getMessage();

            }

            request.setAttribute("error", error);
            request.setAttribute("command", "UPDATEUSER");
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newuser");

        }
        return page;
    }
}