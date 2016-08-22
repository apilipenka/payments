package by.pwt.pilipenko.payments.web.command.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.Command;

import javax.servlet.http.HttpServletRequest;

public class UpdateCommandCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String error = null;
        Command command1 = null;
        String page = null;
        try {
            String id = request.getParameter("id");
            String command = request.getParameter("commandf");
            String url = request.getParameter("url");
            String label = request.getParameter("label");
            String comment = request.getParameter("comment");

            command1 = new Command();
            command1.setId(Integer.parseInt(id));
            command1.setCommand(command);
            command1.setUrl(url);
            command1.setLabel(label);
            command1.setComment(comment);

            CommandService commandService = new CommandService();
            boolean flag = commandService.updateEntity(command1);
            if (flag) {
                request.getSession().setAttribute("success", "true");

                page = ConfigurationManager.getProperty("path.page.commandlist");

                request.getSession().setAttribute("message", "The command has been successfully updated.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("commando", command1);

            request.setAttribute("error", error);
            request.setAttribute("command", "UPDATECOMMAND");
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newcommand");

        }
        return page;
    }
}