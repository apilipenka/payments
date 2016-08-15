package by.pwt.pilipenko.payments.web.command.agreement;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.Agreement;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditAgreementCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newagreement");

        AgreementService agreementService = new AgreementService();
        Object name = request.getAttribute("agreementID");

        Agreement agreement = new Agreement();

        try {
            if (name != null) {

                agreement = agreementService.getEntity(Integer.parseInt(name.toString()));

            } else {
                name = request.getParameter("userID");
                if (name != null) {
                    agreement = agreementService.getEntity(Integer.parseInt(name.toString()));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (agreement != null) {
            request.setAttribute("user", agreement.createAgreementVO());
        }

        request.setAttribute("command", "UPDATEAGREEMENT");


        request.setAttribute("banks", new BankService().getAllEntities());
        request.setAttribute("clients", new UserService().getAllEntities());

        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}