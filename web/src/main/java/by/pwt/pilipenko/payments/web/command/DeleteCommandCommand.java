package by.pwt.pilipenko.payments.web.command;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CommandService;

import javax.servlet.http.HttpServletRequest;

public class DeleteCommandCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        CommandService commandService = new CommandService();
        Object name = request.getAttribute("commandID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                commandService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("commandID");
                if (name != null) {
                    commandService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.commandlist");

            request.getSession().setAttribute("message", "The command has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.commandlist");
            request.setAttribute("error", error);
            request.setAttribute("command", "COMMANDLIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}