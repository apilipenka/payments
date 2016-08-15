package by.pwt.pilipenko.payments.web.command.agreement;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteAgreementCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        AgreementService agreementService = new AgreementService();
        Object name = request.getAttribute("agreementID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                agreementService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("agreementID");
                if (name != null) {
                    agreementService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.agreementlist");

            request.getSession().setAttribute("message", "Agreement has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.agreementlist");
            request.setAttribute("error", error);
            request.setAttribute("command", "AGREEMENTLIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}