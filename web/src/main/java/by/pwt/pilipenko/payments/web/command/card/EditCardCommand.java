package by.pwt.pilipenko.payments.web.command.card;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.AccountService;
import by.pwt.pilipenko.payments.services.CardService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.Card;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditCardCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException {
        String page = ConfigurationManager.getProperty("path.page.newcard");

        CardService cardService = new CardService();
        Object name = request.getAttribute("cardID");

        Card card = new Card();

        try {
            if (name != null) {

                card = cardService.getEntity(Integer.parseInt(name.toString()));

            } else {
                name = request.getParameter("cardID");
                if (name != null) {
                    card = cardService.getEntity(Integer.parseInt(name.toString()));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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