package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.BankService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.pwt.pilipenko.payments.web.command.agreement.AgreementUtil.fillAgreementParent;

public class AgreementFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        BankService bankService = new BankService();

        try {
            fillAgreementParent((HttpServletRequest) req);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}