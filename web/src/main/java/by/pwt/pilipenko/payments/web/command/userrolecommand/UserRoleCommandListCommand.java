package by.pwt.pilipenko.payments.web.command.userrolecommand;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserRoleCommandService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.VO.UserRoleCommandVO;
import by.pwt.plipenko.payments.model.entities.UserRoleCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserRoleCommandListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page = ConfigurationManager.getProperty("path.page.userrolecommandlist");

        UserRoleCommandService userRoleCommandService = new UserRoleCommandService();
        Object name = request.getAttribute("userRoleCommandName");

        List<UserRoleCommand> userRoleCommandList = new ArrayList<UserRoleCommand>();


        if (name != null && name.toString() != "") {
            userRoleCommandList = userRoleCommandService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("userRoleCommandName");
            if (name != null && name.toString() != "") {
                userRoleCommandList = userRoleCommandService.searchEntityByName(name.toString());
            } else {
                userRoleCommandList = userRoleCommandService.getAllEntities();
            }
        }


        if (userRoleCommandList != null) {
            List<UserRoleCommandVO> userRoleCommandVOList = new ArrayList<UserRoleCommandVO>();

            for (UserRoleCommand userRoleCommand : userRoleCommandList) {
                userRoleCommandVOList.add(userRoleCommand.createUserRoleCommandVO());
            }

            if (userRoleCommandVOList != null)
                request.setAttribute("userRoleCommandList", userRoleCommandVOList);
        }
        return page;
    }
}