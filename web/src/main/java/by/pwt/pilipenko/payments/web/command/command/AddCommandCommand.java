package by.pwt.pilipenko.payments.web.command.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.pilipenko.payments.model.entities.Command;

import javax.servlet.http.HttpServletRequest;


public class AddCommandCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        String error = null;
        Command coammand2 = null;
        Command command1 = null;
        String page = null;
        try {
            String command = request.getParameter("commandf");
            String url = request.getParameter("url");
            String label = request.getParameter("label");
            String comment = request.getParameter("comment");

            command1 = new Command();

            command1.setCommand(command);
            command1.setUrl(url);
            command1.setLabel(label);
            command1.setComment(comment);

            CommandService commandService = new CommandService();

            coammand2 = commandService.insertEntity(command1);
            page = ConfigurationManager.getProperty("path.page.commandlist");

            request.getSession().setAttribute("success", "true");
            request.getSession().setAttribute("message", "The new command has been successfully created.");

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("command", command1);

            request.setAttribute("error", error);
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newcommand");

        }
        return page;
    }
}