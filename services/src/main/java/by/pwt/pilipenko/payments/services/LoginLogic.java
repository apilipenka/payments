package by.pwt.pilipenko.payments.services;

import by.pwt.plipenko.payments.model.entities.User;

import javax.naming.NamingException;
import java.sql.SQLException;


public class LoginLogic {

    public static boolean checkAdminLogin(String enterLogin, String enterPass) throws SQLException, NamingException {

        User user = new User();
        user.setLogin(enterLogin);

        UserService userService = new UserService();

        User findedUser = userService.getEntityByPK(user);

        if (findedUser != null) {
            return findedUser.getPassword().equals(enterPass)
                    && findedUser.getUserRole().getName().equalsIgnoreCase("ADMIN");
        }
        return false;
    }

    public static boolean checkManagerLogin(String enterLogin, String enterPass) throws SQLException, NamingException {

        User user = new User();
        user.setLogin(enterLogin);

        UserService userService = new UserService();

        User findedUser = userService.getEntityByPK(user);

        if (findedUser != null) {
            return findedUser.getPassword().equals(enterPass)
                    && findedUser.getUserRole().getName().equalsIgnoreCase("MANAGER");
        }
        return false;
    }

    public static boolean checkUserLogin(String enterLogin, String enterPass) throws SQLException, NamingException {

        User user = new User();
        user.setLogin(enterLogin);

        UserService userService = new UserService();

        User findedUser = userService.getEntityByPK(user);

        if (findedUser != null) {
            return findedUser.getPassword().equals(enterPass)
                    && findedUser.getUserRole().getName().equalsIgnoreCase("CLIENT");
        }
        return false;
    }
}