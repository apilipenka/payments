package by.pwt.pilipenko.payments.web.command.agreement;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.VO.BankVO;
import by.pwt.plipenko.payments.model.VO.UserVO;
import by.pwt.plipenko.payments.model.entities.Bank;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.pwt.pilipenko.payments.web.command.agreement.AgreementUtil.fillAgreementParent;

public class NewAgreementCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newagreemetn");

        fillAgreementParent(request);

        return page;
    }

}