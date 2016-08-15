package by.pwt.pilipenko.payments.web.command.user;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        Object name = request.getAttribute("userID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                userService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("userID");
                if (name != null) {
                    userService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.userlist");

            request.getSession().setAttribute("message", "The user has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.userlist");
            request.setAttribute("error", error);
            request.setAttribute("command", "USERLIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}