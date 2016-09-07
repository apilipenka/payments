package by.pwt.pilipenko.payments.web.command.account;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.model.VO.AccountVO;
import by.pwt.pilipenko.payments.model.entities.Account;
import by.pwt.pilipenko.payments.services.AccountService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountListWithPaginationCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException, ClassNotFoundException {
        String page = ConfigurationManager.getProperty("path.page.accountlistp");

        AccountService accountService = new AccountService();
        Object pg = request.getAttribute("pg");
        Object rpp = request.getAttribute("rpp");
        String name = (String)request.getAttribute("accountName");

        List<Account> accountList;


        if (pg==null) {
            pg = request.getParameter("pg");
        }
        if (pg==null) {
            pg = new Integer(0);
        }

        if (rpp==null) {
            rpp = request.getParameter("rpp");
        }
        if (rpp==null) {
            rpp = new Integer(0);
        }



        if (name != null) {
            accountList = accountService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("accountName");
            if (name != null) {
                accountList = accountService.searchEntityByNameWithPagination(name.toString(),new Integer(pg.toString()), new Integer(rpp.toString()));


                request.setAttribute("accountName", name);
            } else {
                accountList = accountService.getAllEntitiesWithPagination(new Integer(pg.toString()), new Integer(rpp.toString()));
            }
        }


        if (accountList != null) {
            List<AccountVO> accountVOList = new ArrayList<>();

            for (Account account : accountList) {
                accountVOList.add(account.createAccountVO());
            }

            request.setAttribute("pg", new Integer(pg.toString()));
            request.setAttribute("rpp", new Integer(rpp.toString()));

            request.setAttribute("accountList", accountVOList);
        }
        return page;
    }
}