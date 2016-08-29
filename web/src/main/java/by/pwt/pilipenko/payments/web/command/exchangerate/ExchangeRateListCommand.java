package by.pwt.pilipenko.payments.web.command.exchangerate;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.ExchangeRateService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.pilipenko.payments.model.VO.ExchangeRateVO;
import by.pwt.pilipenko.payments.model.entities.ExchangeRate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apilipenka on 8/11/2016.
 */
public class ExchangeRateListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String page = ConfigurationManager.getProperty("path.page.echangeRateList");

        ExchangeRateService exchangeRateService = new ExchangeRateService();
        Object name = request.getAttribute("echangeRateName");

        List<ExchangeRate> exchangeRateList = new ArrayList<ExchangeRate>();


        if (name != null) {
            exchangeRateList = exchangeRateService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("echangeRateName");
            if (name != null) {
                exchangeRateList = exchangeRateService.searchEntityByName(name.toString());
            } else {
                exchangeRateList = exchangeRateService.getAllEntities();
            }
        }


        if (exchangeRateList != null) {
            List<ExchangeRateVO> userVOList = new ArrayList<ExchangeRateVO>();

            for (ExchangeRate exchangeRate : exchangeRateList) {
                userVOList.add(exchangeRate.createExchangeRateVO());
            }

            if (userVOList != null)
                request.setAttribute("exchangeRateList", userVOList);
        }
        return page;
    }
}
