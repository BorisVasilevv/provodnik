package SBapp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


import java.util.ArrayList;


@WebServlet(urlPatterns = {"/provodnik/"})
public class JspServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path=req.getParameter("path");
        if((path==null)) {
            path = System.getProperty("user.home");
            resp.sendRedirect(String.format("%s%s?path=%s", req.getContextPath(), req.getServletPath(), URLEncoder.encode(path, StandardCharsets.UTF_8.toString())));
        }


        setParamsToModel(req,resp,path);
        req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }



    public void setParamsToModel(HttpServletRequest req, HttpServletResponse resp, String path){
        File dir = new File(path);
        ArrayList<File> files= new ArrayList<File>();
        ArrayList<File> directories = new ArrayList<File>();

        for (File file:dir.listFiles()) {
            if(file.isFile()) files.add(file);
            else directories.add(file);
        }

        req.setAttribute("path", path);
        req.setAttribute("files", files);
        req.setAttribute("directories",directories);

    }


}
