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
        long count = 0;


        if (pg==null) {
            pg = request.getParameter("pg");
        }
        if (pg==null || pg.equals("")) {
            pg = new Integer(1);
        }

        if (rpp==null) {
            rpp = request.getParameter("rpp");
        }
        if (rpp==null || rpp.equals("")) {
            rpp = new Integer(1);
        }



        if (name != null && !name.equals("")) {
            accountList = accountService.searchEntityByNameWithPagination(name.toString(),new Integer(pg.toString()), new Integer(rpp.toString()));
            count =  accountService.getCountEntityByNameWithPagination(name.toString());
            request.setAttribute("accountName",name);
        } else {
            name = request.getParameter("accountName");
            if (name != null && !name.equals("")) {
                accountList = accountService.searchEntityByNameWithPagination(name.toString(),new Integer(pg.toString()), new Integer(rpp.toString()));
                count =  accountService.getCountEntityByNameWithPagination(name.toString());

                request.setAttribute("accountName", name);
            } else {
                accountList = accountService.getAllEntitiesWithPagination(new Integer(pg.toString()), new Integer(rpp.toString()));
                count = accountService.getCountAllEntitiesWithPagination();
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
        request.setAttribute("recordsCount",count);
        request.setAttribute("maxPage", (int)Math.ceil((float)count/new Integer(rpp.toString())));

        return page;
    }
}