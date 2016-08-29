package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.AccountService;
import by.pwt.pilipenko.payments.model.VO.AccountVO;
import by.pwt.pilipenko.payments.model.entities.Account;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("accountList");
        AccountService userService = new AccountService();

        List<Account> accountList = null;

        if (name == null) {
            name = req.getParameter("accountList");
            if (name == null) {
                try {
                    accountList = userService.getAllEntities();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (accountList != null) {
            List<AccountVO> accountVOList = new ArrayList<AccountVO>();
            for (Account account : accountList) {
                accountVOList.add(account.createAccountVO());
            }
            req.setAttribute("accountList", accountVOList);
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}