package by.pwt.pilipenko.payments.web.command;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CommandService;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditCommandCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newcommand");

        CommandService commandService = new CommandService();
        Object name = request.getAttribute("commandID");

        try {
            if (name != null) {

                request.setAttribute("command", commandService.getEntity(Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("commandID");
                if (name != null) {
                    request.setAttribute("command", commandService.getEntity(Integer.parseInt(name.toString())));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("command", "UPDATECOMMAND");
        return page;
    }
}