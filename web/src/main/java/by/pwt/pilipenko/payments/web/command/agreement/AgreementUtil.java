package by.pwt.pilipenko.payments.web.command.agreement;

import by.pwt.pilipenko.payments.services.BankService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.VO.BankVO;
import by.pwt.plipenko.payments.model.VO.UserVO;
import by.pwt.plipenko.payments.model.entities.Bank;
import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apilipenka on 8/15/2016.
 */
public class AgreementUtil {

    public static void fillAgreementParent(HttpServletRequest request) {
        BankService bankService = new BankService();

        try {

            List<Bank> banks = bankService.getAllEntities();
            if (banks != null) {
                List<BankVO> banksVO = new ArrayList<BankVO>();
                for (Bank bank : banks) {
                    banksVO.add(bank.createBankVO());

                }
                request.setAttribute("banks", banksVO);
            }
        } catch (SQLException | NamingException e) {
            // e.printStackTrace();
            // TODO add error handler
        }

        UserService userService = new UserService();

        try {
            List<User> users = userService.getAllEntities();
            if (users != null) {
                List<UserVO> usersVO = new ArrayList<UserVO>();
                for (User user : users) {
                    usersVO.add(user.createUserVO());

                }
                request.setAttribute("clients", usersVO);
            }
        } catch (SQLException | NamingException e) {
            // e.printStackTrace();
            // TODO add error handler
        }
    }

}
