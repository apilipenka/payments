package by.pwt.pilipenko.payments.web.command.agreement;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.Agreement;
import by.pwt.plipenko.payments.model.entities.Bank;
import by.pwt.plipenko.payments.model.entities.User;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddAgreementCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {




        String error = null;
        Agreement agreement = null;
        Agreement agreement1 = null;
        String page = null;
        try {

            String number = request.getParameter("number");

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            String validFromDateStr = request.getParameter("validFromDate");
            Date validFromDate = format.parse(validFromDateStr);

            String validToDateStr = request.getParameter("validToDate");
            Date validToDate = format.parse(validToDateStr);

            String bankStr = request.getParameter("bank");
            String userStr = request.getParameter("client");



            BankService bankService = new BankService();

            Bank bank = null;
            try {
                bank = bankService.getEntity(Integer.parseInt(bankStr));
            } catch (Exception e) {
                throw e;
            }

            UserService userService = new UserService();

            User client = null;
            try {
                client = userService.getEntity(Integer.parseInt(userStr));
            } catch (Exception e) {
                throw e;
            }

            agreement = new Agreement();
            agreement.setNumber(number);
            agreement.setValidFromDate(validFromDate);
            agreement.setValidToDate(validToDate);
            agreement.setBank(bank);
            agreement.setClient(client);


            AgreementService agreementService = new AgreementService();
            agreement1 = agreementService.insertEntity(agreement);
            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.agreementlist");
            request.getSession().setAttribute("message", "New agreement has been successfully created.");


        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("agreement", agreement.createAgreementVO());

            try {
                request.setAttribute("banks", new BankService().getAllEntities());
            } catch (NamingException e1) {
                error += "<br/>" + e1.getMessage();

            } catch (SQLException e1) {
                error += "<br/>" + e1.getMessage();

            }

            try {
                request.setAttribute("clients", new UserService().getAllEntities());
            } catch (NamingException e1) {
                error += "<br/>" + e1.getMessage();

            } catch (SQLException e1) {
                error += "<br/>" + e1.getMessage();

            }

            request.setAttribute("error", error);
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newagreement");

        }
        return page;
    }
}