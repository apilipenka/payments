package by.pwt.pilipenko.payments.web.command.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.VO.CommandVO;
import by.pwt.plipenko.payments.model.entities.Command;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.commandlist");

        CommandService commandService = new CommandService();
        Object name = request.getAttribute("commandName");

        List<Command> commandList = new ArrayList<Command>();


        if (name != null) {
            commandList = commandService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("commandName");
            if (name != null) {
                commandList = commandService.searchEntityByName(name.toString());
            } else {
                commandList = commandService.getAllEntities();
            }
        }


        if (commandList != null) {
            List<CommandVO> commandVOList = new ArrayList<CommandVO>();

            for (Command command : commandList) {
                commandVOList.add(command.createCommandVO());
            }

            if (commandVOList != null)
                request.setAttribute("commandList", commandVOList);
        }
        return page;
    }
}