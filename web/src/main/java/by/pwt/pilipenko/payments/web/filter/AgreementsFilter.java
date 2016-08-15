package by.pwt.pilipenko.payments.web.filter;

import by.pwt.pilipenko.payments.services.AgreementService;
import by.pwt.plipenko.payments.model.VO.AgreementVO;
import by.pwt.plipenko.payments.model.entities.Agreement;

import javax.naming.NamingException;
import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgreementsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Object name = req.getAttribute("agreementList");
        AgreementService agreementService = new AgreementService();

        List<Agreement> agreementList = null;

        if (name == null) {
            name = req.getParameter("agreementList");
            if (name == null) {
                try {
                    agreementList = agreementService.getAllEntities();
                } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                }
            }
        }

        if (agreementService != null) {
            List<AgreementVO> agreementVOList = new ArrayList<AgreementVO>();
            for (Agreement agreement : agreementList) {
                agreementVOList.add(agreement.createAgreementVO());
            }
            req.setAttribute("agreementList", agreementVOList);
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}