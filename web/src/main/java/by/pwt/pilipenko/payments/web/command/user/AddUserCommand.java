package by.pwt.pilipenko.payments.web.command.user;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.User;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddUserCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        String error = null;
        User user1 = null;
        User user = null;
        String page = null;
        try {
            String login = request.getParameter("login");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String personalNumber = request.getParameter("personalNumber");
            String role = request.getParameter("role");
            String birthDateStr = request.getParameter("birthDate");
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate = format.parse(birthDateStr);


            UserRoleService userRoleService = new UserRoleService();

            UserRole userRole = null;
            try {
                userRole = userRoleService.getEntity(Integer.parseInt(role));
            } catch (Exception e) {
                throw e;
            }

            user = new User();

            user.setLogin(login);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setPersonalNumber(personalNumber);
            user.setUserRole(userRole);
            user.setBirthDate(birthDate);

            UserService userService = new UserService();

            user1 = userService.insertEntity(user);
            page = ConfigurationManager.getProperty("path.page.login");

            request.getSession().setAttribute("success", "true");

            if (!request.getParameter("source").equalsIgnoreCase("LOGIN")) {
                page = ConfigurationManager.getProperty("path.page.userlist");
                request.getSession().setAttribute("message", "The new user has been successfully created.");
            } else {
                request.getSession().setAttribute("message", "The new user has been successfully registered.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("user", user);

            try {
                request.setAttribute("roles", new UserRoleService().getAllEntities());
            } catch (NamingException e1) {
                error += "<br/>" + e1.getMessage();

            } catch (SQLException e1) {
                error += "<br/>" + e1.getMessage();

            }

            request.setAttribute("error", error);
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newuser");

        }
        return page;
    }
}