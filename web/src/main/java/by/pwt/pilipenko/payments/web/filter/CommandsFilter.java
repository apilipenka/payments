package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.CommandService;
import by.pwt.plipenko.payments.model.VO.CommandVO;
import by.pwt.plipenko.payments.model.entities.Command;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("commandList");
        CommandService commandService = new CommandService();

        List<Command> commandList = null;

        if (name == null) {
            name = req.getParameter("commandList");
            if (name == null) {
                try {
                    commandList = commandService.getAllEntities();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }
            }
        }

        if (commandList != null) {
            List<CommandVO> userVOList = new ArrayList<CommandVO>();
            for (Command command : commandList) {
                userVOList.add(command.createCommandVO());
            }
            req.setAttribute("commandList", userVOList);
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}