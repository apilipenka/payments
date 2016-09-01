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

public class AccountListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException, ClassNotFoundException {
        String page = ConfigurationManager.getProperty("path.page.accountlist");

        AccountService accountService = new AccountService();
        Object name = request.getAttribute("accountName");

        List<Account> accountList;


        if (name != null) {
            accountList = accountService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("accountName");
            if (name != null) {
                accountList = accountService.searchEntityByName(name.toString());
            } else {
                accountList = accountService.getAllEntities();
            }
        }


        if (accountList != null) {
            List<AccountVO> accountVOList = new ArrayList<AccountVO>();

            for (Account account : accountList) {
                accountVOList.add(account.createAccountVO());
            }

            request.setAttribute("accountList", accountVOList);
        }
        return page;
    }
}