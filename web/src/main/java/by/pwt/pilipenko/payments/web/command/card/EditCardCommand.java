package by.pwt.pilipenko.payments.web.command.card;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.AccountService;
import by.pwt.pilipenko.payments.services.CardService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.pilipenko.payments.model.entities.Card;

import javax.servlet.http.HttpServletRequest;

public class EditCardCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page = ConfigurationManager.getProperty("path.page.newcard");

        CardService cardService = new CardService();
        Object name = request.getAttribute("cardID");

        Card card = new Card();


        if (name != null) {

            card = cardService.getEntity(Integer.parseInt(name.toString()));

        } else {
            name = request.getParameter("cardID");
            if (name != null) {
                card = cardService.getEntity(Integer.parseInt(name.toString()));
            }
        }


        if (card != null) {
            request.setAttribute("card", card.createCardVO());
        }

        request.setAttribute("command", "UPDATECARD");
        request.setAttribute("accounts", new AccountService().getAllEntities());
        request.setAttribute("source", request.getParameter("source"));
        return page;
    }
}