package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.VO.BankVO;
import by.pwt.plipenko.payments.model.VO.UserVO;
import by.pwt.plipenko.payments.model.entities.Bank;
import by.pwt.plipenko.payments.model.entities.User;
import sun.swing.BakedArrayList;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.pwt.pilipenko.payments.web.command.agreement.AgreementUtil.fillAgreementParent;

public class AgreementFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        BankService bankService = new BankService();

        fillAgreementParent((HttpServletRequest) req);
        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}