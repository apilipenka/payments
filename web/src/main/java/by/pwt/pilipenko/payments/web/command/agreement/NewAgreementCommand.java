package by.pwt.pilipenko.payments.web.command.agreement;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static by.pwt.pilipenko.payments.web.command.agreement.AgreementUtil.fillAgreementParent;

public class NewAgreementCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException, ClassNotFoundException {
        String page = ConfigurationManager.getProperty("path.page.newagreemetn");

        fillAgreementParent(request);

        return page;
    }

}