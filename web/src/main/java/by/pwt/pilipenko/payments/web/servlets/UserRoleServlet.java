package by.pwt.pilipenko.payments.web.servlets;

import by.pwt.pilipenko.payments.services.UserRoleService;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "UserRoleServlet", urlPatterns = {"/userRole", "/jsp/userRole"})
public class UserRoleServlet extends HttpServlet {

    private static final long serialVersionUID = -3433738570980729189L;
    UserRoleService userRoleService = new UserRoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if (action != null) {
            switch (action) {
                case "searchUserRoleById":
                    searchUserRoleById(req, resp);
                    break;
                case "searchUserRoleByName":
                    try {
                        searchUserRoleByName(req, resp);
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
            List<UserRole> result = null;
            try {
                result = userRoleService.getAllEntities();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            forwardUserRolesList(req, resp, result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "addUserRole":
                try {
                    addUserRoleAction(req, resp);
                } catch (NamingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "editUserRole":
                try {
                    editUserRoleAction(req, resp);
                } catch (NamingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "removeUserRole":
                try {
                    removeUserRoleByID(req, resp);
                } catch (NamingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }

    }

    private void searchUserRoleById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int userRoleID = Integer.valueOf(req.getParameter("userRoleID"));
        UserRole userRole = null;
        try {
            userRole = userRoleService.getEntity(userRoleID);
        } catch (Exception ex) {
            Logger.getLogger(UserRoleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("userRole", userRole);

        req.setAttribute("action", "editUserRole");
        String nextJSP = "/jsp/new-userRole.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void searchUserRoleByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, NamingException {
        String userRoleName = req.getParameter("userRoleName");

        List<UserRole> result = userRoleService.searchEntityByName(userRoleName);

        forwardUserRolesList(req, resp, result);
    }

    private void forwardUserRolesList(HttpServletRequest req, HttpServletResponse resp, List<UserRole> userRolesList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/userRole-list.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("userRoleList", userRolesList);
        dispatcher.forward(req, resp);
    }

    private void addUserRoleAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, NamingException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        UserRole userRole = new UserRole();

        userRole.setName(name);
        userRole.setDescription(description);

        userRole = userRoleService.insertEntity(userRole);

        List<UserRole> list = userRoleService.getAllEntities();

        req.setAttribute("userRoleID", userRole.getId());
        String message = "The new user role has been successfully created.";
        req.setAttribute("message", message);
        forwardUserRolesList(req, resp, list);
    }

    private void editUserRoleAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, NamingException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        UserRole userRole = new UserRole(id, name, description);

        boolean success = userRoleService.updateEntity(userRole);

        String message = null;
        if (success) {
            message = "The user role has been successfully updated.";
        }
        List<UserRole> list = userRoleService.getAllEntities();
        req.setAttribute("userRoleID", userRole.getId());
        req.setAttribute("message", message);
        forwardUserRolesList(req, resp, list);
    }

    private void removeUserRoleByID(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException, NamingException {
        int id = Integer.valueOf(req.getParameter("userRoleID"));

        boolean confirm = userRoleService.deleteEntity(id);
        if (confirm) {
            String message = "The user role has been successfully removed.";
            req.setAttribute("message", message);
        }
        List<UserRole> list = userRoleService.getAllEntities();
        forwardUserRolesList(req, resp, list);
    }

}
