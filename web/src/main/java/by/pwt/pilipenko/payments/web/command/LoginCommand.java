package by.pwt.pilipenko.payments.web.command;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.dao.resources.MessageManager;
import by.pwt.pilipenko.payments.services.LoginLogic;

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
        if (LoginLogic.checkAdminLogin(login, pass)) {
            request.setAttribute("user", login);
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "ADMIN");
            // определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.main");
        } else if (LoginLogic.checkUserLogin(login, pass)) {
            request.setAttribute("user", login);
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "CLIENT");
            // определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.user");
        } else if (LoginLogic.checkManagerLogin(login, pass)) {
            request.setAttribute("user", login);
            HttpSession session = request.getSession(true);
            session.setAttribute("userType", "MANAGER");
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
