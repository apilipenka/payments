package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.VO.BankVO;
import by.pwt.plipenko.payments.model.VO.UserVO;
import by.pwt.plipenko.payments.model.entities.Bank;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanksFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("bankList");
        BankService bankService = new BankService();

        List<Bank> bankList = null;

        if (name == null) {
            name = req.getParameter("bankList");
            if (name == null) {
                try {
                    bankList = bankService.getAllEntities();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }
            }
        }

        if (bankList != null) {
            List<BankVO> bankVOList = new ArrayList<BankVO>();
            for (Bank bank : bankList) {
                bankVOList.add(bank.createBankVO());
            }
            req.setAttribute("bankList", bankVOList);
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}