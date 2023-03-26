package Servlets;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/","/just-servlet", "/servlet/*"})
public class TestServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("init");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("method service start \n");
        super.service(req, resp);
        resp.getWriter().write("method service exit \n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.getWriter().write("method doGet \n");
        String uri= req.getRequestURI();
        String params= formatting(req);
        resp.getWriter().write("GET\n"+ uri + "\nparams: \n"+params+ '\n');
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri= req.getRequestURI();
        String params= formatting(req);
        resp.getWriter().write("POST\n"+uri + "\nparams: \n"+params+ '\n');
    }

    private String formatting(HttpServletRequest req){

        return req.getParameterMap().entrySet().stream().map(
                entry -> { String p=String.join(" and ", entry.getValue());
                    return entry.getKey() + " : " + p;
                }
        ).collect(Collectors.joining("\n"));
    }

    @Override
    public void destroy() {
        log("destroy");
    }
}
