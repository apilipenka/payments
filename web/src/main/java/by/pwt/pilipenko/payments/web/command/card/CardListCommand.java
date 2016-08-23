package by.pwt.pilipenko.payments.web.command.card;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.CardService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.plipenko.payments.model.VO.CardVO;
import by.pwt.plipenko.payments.model.entities.Card;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException, ClassNotFoundException {
        String page = ConfigurationManager.getProperty("path.page.cardlist");

        CardService cardService = new CardService();
        Object name = request.getAttribute("cardName");

        List<Card> cardList = new ArrayList<Card>();


        if (name != null) {
            cardList = cardService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("cardName");
            if (name != null) {
                cardList = cardService.searchEntityByName(name.toString());
            } else {
                cardList = cardService.getAllEntities();
            }
        }


        if (cardList != null) {
            List<CardVO> cardVOList = new ArrayList<CardVO>();

            for (Card card : cardList) {
                cardVOList.add(card.createCardVO());
            }

            if (cardVOList != null)
                request.setAttribute("cardList", cardVOList);
        }
        return page;
    }
}