package by.pwt.pilipenko.payments.web.command.card;


import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CardService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class DeleteCardCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        CardService cardService = new CardService();
        Object name = request.getAttribute("cardID");
        String error = null;
        String page = null;
        try {
            if (name != null) {

                cardService.deleteEntity((Integer.parseInt(name.toString())));

            } else {
                name = request.getParameter("cardID");
                if (name != null) {
                    cardService.deleteEntity((Integer.parseInt(name.toString())));
                }
            }

            request.getSession().setAttribute("success", "true");

            page = ConfigurationManager.getProperty("path.page.cardlist");

            request.getSession().setAttribute("message", "Card has been successfully deleted.");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            error = e.getMessage();
            page = ConfigurationManager.getProperty("path.page.cardlist");
            request.setAttribute("error", error);
            request.setAttribute("command", "CARDLIST");
            request.getSession().setAttribute("success", "false");
        }

        return page;
    }
}