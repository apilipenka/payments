package by.pwt.pilipenko.payments.web.command.agreement;

import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.pilipenko.payments.model.VO.AgreementVO;
import by.pwt.pilipenko.payments.model.entities.Agreement;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgreementListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, NamingException, ClassNotFoundException {
        String page = ConfigurationManager.getProperty("path.page.agreementlist");

        AgreementService agreementService = new AgreementService();
        Object name = request.getAttribute("agreementName");

        List<Agreement> agreementList = new ArrayList<Agreement>();


        if (name != null) {
            agreementList = agreementService.searchEntityByName(name.toString());
        } else {
            name = request.getParameter("agreementName");
            if (name != null) {
                agreementList = agreementService.searchEntityByName(name.toString());
            } else {
                agreementList = agreementService.getAllEntities();
            }
        }


        if (agreementList != null) {
            List<AgreementVO> agreementVOList = new ArrayList<AgreementVO>();

            for (Agreement agreement : agreementList) {
                agreementVOList.add(agreement.createAgreementVO());
            }

            if (agreementVOList != null)
                request.setAttribute("agreementList", agreementVOList);
        }
        return page;
    }
}