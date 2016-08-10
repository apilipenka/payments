package by.pwt.pilipenko.payments.web.command;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserRoleService;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditBankCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newbank");

        BankService bankService = new BankService();
        Object name = request.getAttribute("bankID");

        try {
            if (name != null) {

                request.setAttribute("bank", bankService.getEntity(Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("bankID");
                if (name != null) {
                    request.setAttribute("bank", bankService.getEntity(Integer.parseInt(name.toString())));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("command", "UPDATEBANK");
        return page;
    }
}