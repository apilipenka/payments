package by.pwt.pilipenko.payments.web.command.bank;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class BankListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.banklist");

        BankService bankService = new BankService();
        Object name = request.getAttribute("bankName");

        if (name != null) {
            request.setAttribute("bankList", bankService.searchEntityByName(name.toString()));
        } else {
            name = request.getParameter("bankName");
            if (name != null) {
                request.setAttribute("bankList", bankService.searchEntityByName(name.toString()));
            } else {
                request.setAttribute("bankList", bankService.getAllEntities());
            }
        }
        return page;
    }
}