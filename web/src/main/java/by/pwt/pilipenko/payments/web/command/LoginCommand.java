package by.pwt.pilipenko.payments.web.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.dao.resources.MessageManager;
import by.pwt.pilipenko.payments.model.entities.User;
import by.pwt.pilipenko.payments.services.LoginLogic;
import by.pwt.pilipenko.payments.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request) throws Exception {
        String page = null;
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        // проверка логина и пароля

        User user = new User();
        user.setLogin(login);

        UserService userService = new UserService();

        if (LoginLogic.checkAdminLogin(login, pass)) {
            request.setAttribute("user", login);
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "ADMIN");



            User user1 = userService.getEntityByPK(user);
            session.setAttribute("user",user1);
            // определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.main");
        } else if (LoginLogic.checkAdminLogin(login, pass)) {
            request.setAttribute("user", login);
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "CLIENT");
            User user1 = userService.getEntityByPK(user);
            session.setAttribute("user",user1);
            // определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.user");
        } else if (LoginLogic.checkAdminLogin(login, pass)) {
            request.setAttribute("user", login);
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "MANAGER");
            User user1 = userService.getEntityByPK(user);
            session.setAttribute("user",user1);
            // определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.manager");
        } else {
            request.setAttribute("message", MessageManager.getProperty("message.loginerror"));
            request.getSession().setAttribute("userType", "GUEST");
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
