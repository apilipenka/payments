package by.pwt.pilipenko.payments.web.servlets;

import by.pwt.pilipenko.payments.services.UserRoleService;
import by.pwt.pilipenko.payments.services.UserService;
import by.pwt.plipenko.payments.model.entities.User;
import by.pwt.plipenko.payments.model.entities.UserRole;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "UserServlet", urlPatterns = {"/user", "/jsp/user"})
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = -3433738570980729189L;
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if (action != null) {
            switch (action) {
                case "searchUserById":
                    try {
                        searchUserById(req, resp);
                    } catch (NamingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    break;
                case "searchUserByName":
                    try {
                        searchUserByName(req, resp);
                    } catch (NamingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            List<User> result = null;
            try {
                result = userService.getAllEntities();
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            forwardUsersList(req, resp, result);
        }
    }

    private void searchUserById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, NamingException {

        int userID = Integer.valueOf(req.getParameter("userID"));
        User user = null;
        try {
            user = userService.getEntity(userID);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("user", user);

        UserRoleService userRoleService = new UserRoleService();

        req.setAttribute("roles", userRoleService.getAllEntities());

        req.setAttribute("action", "editUser");
        String nextJSP = "/jsp/new-user.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void searchUserByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, NamingException {
        String userName = req.getParameter("userName");

        List<User> result = userService.searchEntityByName(userName);

        forwardUsersList(req, resp, result);
    }

    private void forwardUsersList(HttpServletRequest req, HttpServletResponse resp, List<User> usersList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/user-list.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("userList", usersList);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "addUser":
                try {
                    addUserAction(req, resp);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (NamingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                break;
            case "editUser":
                try {
                    editUserAction(req, resp);
                } catch (NamingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                break;
            case "removeUser":
                try {
                    removeUserByID(req, resp);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NamingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }

    }

    private void addUserAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, NamingException {
        String login = req.getParameter("login");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password = req.getParameter("password");
        String personalNumber = req.getParameter("personalNumber");
        String role = req.getParameter("role");

        UserRoleService userRoleService = new UserRoleService();

        UserRole userRole = null;
        try {
            userRole = userRoleService.getEntity(Integer.parseInt(role));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        User user = new User();

        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setPersonalNumber(personalNumber);
        user.setUserRole(userRole);

        user = userService.insertEntity(user);

        List<User> list1 = userService.getAllEntities();

        req.setAttribute("userID", user.getId());
        String message = "The new user has been successfully created.";
        req.setAttribute("message", message);
        forwardUsersList(req, resp, list1);
    }

    private void editUserAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, NamingException {

        int id = Integer.parseInt(req.getParameter("id"));
        String login = req.getParameter("login");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password = req.getParameter("password");
        String personalNumber = req.getParameter("personalNumber");
        int roleId = Integer.parseInt(req.getParameter("role"));


        String birthDateStr = req.getParameter("startDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = null;
        try {
            birthDate = sdf.parse(birthDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserRoleService userRoleService = new UserRoleService();

        UserRole role = null;
        try {
            role = userRoleService.getEntity(roleId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        User user = new User(id, login, firstName, lastName, password, personalNumber, role, birthDate);


        boolean success = userService.updateEntity(user);

        String message = null;
        if (success) {
            message = "The user has been successfully updated.";
        }
        List<User> usersList = userService.getAllEntities();
        req.setAttribute("userID", user.getId());
        req.setAttribute("message", message);
        forwardUsersList(req, resp, usersList);
    }

    private void removeUserByID(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, NamingException {
        int id = Integer.valueOf(req.getParameter("userID"));

        boolean confirm = userService.deleteEntity(id);
        if (confirm) {
            String message = "The user has been successfully removed.";
            req.setAttribute("message", message);
        }
        List<User> usersList = userService.getAllEntities();
        forwardUsersList(req, resp, usersList);
    }

}
