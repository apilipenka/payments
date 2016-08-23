package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.CardService;
import by.pwt.plipenko.payments.model.VO.CardVO;
import by.pwt.plipenko.payments.model.entities.Card;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("cardList");
        CardService cardService = new CardService();

        List<Card> cardList = null;

        if (name == null) {
            name = req.getParameter("cardList");
            if (name == null) {
                try {
                    cardList = cardService.getAllEntities();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (cardList != null) {
            List<CardVO> cardVOList = new ArrayList<CardVO>();
            for (Card card : cardList) {
                cardVOList.add(card.createCardVO());
            }
            req.setAttribute("cardList", cardVOList);
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}