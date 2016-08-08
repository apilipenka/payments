package by.pwt.pilipenko.payments.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(dispatcherTypes = {
//    DispatcherType.REQUEST,
//    DispatcherType.FORWARD,
//    DispatcherType.INCLUDE
//    }, urlPatterns = { "/controller","/jsp/*","/*" })
@WebFilter(urlPatterns = {"/controller", "/jsp/*", "/*"})
public class ServletSecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String type = (String) session.getAttribute("userType");

        if (type == null) {
            type = "GUEST";
            session.setAttribute("userType", type);

            resp.sendRedirect(req.getContextPath());

            return;

        } else if (!type.equalsIgnoreCase("ADMIN")
                && (req.getRequestURI().replace(req.getContextPath(), "").equalsIgnoreCase("/jsp/admin.jsp")
                || req.getRequestURI().replace(req.getContextPath(), "").equalsIgnoreCase("/admin.jsp"))) {
            resp.sendRedirect(req.getContextPath());

            return;
        } else if (!type.equalsIgnoreCase("USER")
                && (req.getRequestURI().replace(req.getContextPath(), "").equalsIgnoreCase("/jsp/user.jsp")
                || req.getRequestURI().replace(req.getContextPath(), "").equalsIgnoreCase("/user.jsp"))) {
            resp.sendRedirect(req.getContextPath());

            return;
        } else if (!type.equalsIgnoreCase("MANAGER")
                && (req.getRequestURI().replace(req.getContextPath(), "").equalsIgnoreCase("/jsp/manager.jsp")
                || req.getRequestURI().replace(req.getContextPath(), "").equalsIgnoreCase("/manager.jsp"))) {
            resp.sendRedirect(req.getContextPath());

            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}
