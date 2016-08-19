package by.pwt.pilipenko.payments.web.command.card;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.AccountService;
import by.pwt.pilipenko.payments.services.CardService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.entities.Account;
import by.pwt.plipenko.payments.model.entities.Card;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateCardCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String error = null;
        Card card1 = null;
        Card card = null;
        String page = null;
        try {
            int id = new Integer(request.getParameter("id"));
            String number = request.getParameter("number");
            String name = request.getParameter("name");
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String validToDateStr = request.getParameter("validToDate");
            Date validToDate = format.parse(validToDateStr);
            String accountStr = request.getParameter("account");

            AccountService accountService = new AccountService();

            Account account = null;
            try {
                account = accountService.getEntity(Integer.parseInt(accountStr));
            } catch (Exception e) {
                throw e;
            }

            card = new Card();

            card.setNumber(number);
            card.setName(name);
            card.setValidToDate(validToDate);
            card.setAccount(account);

            CardService cardService = new CardService();

            boolean flag = cardService.updateEntity(card);

            if (flag) {
                request.getSession().setAttribute("success", "true");

                page = ConfigurationManager.getProperty("path.page.cardlist");

                request.getSession().setAttribute("message", "Card has been successfully updated.");
            }

        } catch (Exception e) {
            error = e.getMessage();
            request.setAttribute("source", request.getParameter("source"));
            request.setAttribute("card", card.createCardVO());

            try {
                request.setAttribute("accounts", new AccountService().getAllEntities());
            } catch (NamingException e1) {
                error += "<br/>" + e1.getMessage();

            } catch (SQLException e1) {
                error += "<br/>" + e1.getMessage();

            }

            request.setAttribute("error", error);
            request.setAttribute("command", "UPDATECARD");
            request.getSession().setAttribute("success", "false");

            page = ConfigurationManager.getProperty("path.page.newcard");

        }
        return page;
    }
}