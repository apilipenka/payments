package by.pwt.pilipenko.payments.web.servlets;

import by.pwt.pilipenko.payments.dao.DaoFactoryFactory;
import by.pwt.pilipenko.payments.dao.resources.ConfigurationManager;
import by.pwt.pilipenko.payments.dao.resources.MessageManager;
import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.pilipenko.payments.web.command.factory.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller", "/jsp/controller"})
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1144398084066913555L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.getSession().setAttribute("success", null);
        request.getSession().setAttribute("message", null);
        request.getSession().setAttribute("error", null);

        String page = null;

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        page = command.execute(request);


        //DaoFactoryFactory.getInstance().endTransaction();

        if (page != null) {

            Object success = request.getSession().getAttribute("success");


            if (success != null && success.toString().equalsIgnoreCase("true")) {
                response.sendRedirect(request.getContextPath() + page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }

        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));

            response.sendRedirect(request.getContextPath() + page);
        }
    }
}