package by.pwt.pilipenko.payments.web.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.VO.UserVO;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.userlist");

        UserService userService = new UserService();
        Object name = request.getAttribute("userName");

        List<User> userList = new ArrayList<User>();


        if (name != null) {
            userList = userService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("userName");
            if (name != null) {
                userList = userService.searchEntityByName(name.toString());
            } else {
                userList = userService.getAllEntities();
            }
        }


        if (userList != null) {
            List<UserVO> userVOList = new ArrayList<UserVO>();

            for (User user : userList) {
                userVOList.add(user.createUserVO());
            }

            if (userVOList != null)
                request.setAttribute("userList", userVOList);
        }
        return page;
    }
}