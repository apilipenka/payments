package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.dao.BaseDAOFactory;
import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import org.hibernate.Session;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//@WebFilter(dispatcherTypes = {
//    DispatcherType.REQUEST,
//    DispatcherType.FORWARD,
//    DispatcherType.INCLUDE
//    }, urlPatterns = { "/controller","/jsp/*","/*" })
//@WebFilter(urlPatterns = {"/controller", "/jsp/*", "/*"})
public class SessionCloseFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        BaseDAOFactory daoFactory = null;
        try {
            daoFactory = DaoFactoryFactory.getInstance();
        } catch (NamingException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (daoFactory!=null && daoFactory instanceof by.pwt.pilipenko.payments.dao.hibernate.AbstractDAOFactory) {
            try {
                ((by.pwt.pilipenko.payments.dao.hibernate.AbstractDAOFactory)daoFactory).closeSession();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}
