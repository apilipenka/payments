package by.pwt.pilipenko.payments.web.command.account;

import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.pilipenko.payments.services.CurrencyService;
import by.pwt.plipenko.payments.model.VO.AgreementVO;
import by.pwt.plipenko.payments.model.entities.Agreement;
import by.pwt.plipenko.payments.model.entities.Currency;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apilipenka on 8/15/2016.
 */
public class AccountUtil {

    public static void fillAccountParent(HttpServletRequest request) {
        AgreementService agreementService = new AgreementService();

        try {

            List<Agreement> agreements = agreementService.getAllEntities();
            if (agreements != null) {
                List<AgreementVO> agreementsVO = new ArrayList<AgreementVO>();
                for (Agreement agreement : agreements) {
                    agreementsVO.add(agreement.createAgreementVO());

                }
                request.setAttribute("agreements", agreementsVO);
            }
        } catch (SQLException | NamingException e) {
            // e.printStackTrace();
            // TODO add error handler
        }

        CurrencyService currencyService = new CurrencyService();

        try {
            List<Currency> currencies = currencyService.getAllEntities();
            if (currencies != null) {
                request.setAttribute("currencies", currencies);
            }
        } catch (SQLException | NamingException e) {
            // e.printStackTrace();
            // TODO add error handler
        }
    }

}
