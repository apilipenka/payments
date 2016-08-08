package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.VO.UserVO;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("userList");
        UserService userService = new UserService();

        List<User> userList = null;

        if (name == null) {
            name = req.getParameter("userList");
            if (name == null) {
                try {
                    userList = userService.getAllEntities();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }
            }
        }

        if (userList != null) {
            List<UserVO> userVOList = new ArrayList<UserVO>();
            for (User user : userList) {
                userVOList.add(user.createUserVO());
            }
            req.setAttribute("userList", userVOList);
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}