package by.pwt.pilipenko.payments.web.command;


import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    String execute(HttpServletRequest request) throws Exception;
}