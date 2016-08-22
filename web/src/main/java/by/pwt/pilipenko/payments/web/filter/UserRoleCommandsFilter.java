package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.UserRoleCommandService;
import by.pwt.plipenko.payments.model.VO.UserRoleCommandVO;
import by.pwt.plipenko.payments.model.entities.UserRoleCommand;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleCommandsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("userRoleCommandList");
        UserRoleCommandService userRoleCommandService = new UserRoleCommandService();

        List<UserRoleCommand> userRoleCommandList = null;

        if (name == null) {
            name = req.getParameter("userRoleCommandList");
            if (name == null) {
                try {
                    userRoleCommandList = userRoleCommandService.getAllEntities();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }
            }
        }

        if (userRoleCommandList != null) {
            List<UserRoleCommandVO> userRoleCommandVOList = new ArrayList<UserRoleCommandVO>();
            for (UserRoleCommand userRoleCommand : userRoleCommandList) {
                userRoleCommandVOList.add(userRoleCommand.createUserRoleCommandVO());
            }
            req.setAttribute("userRoleCommandList", userRoleCommandVOList);
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}