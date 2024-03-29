package Servlets;
import accounts.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;


@WebServlet(urlPatterns = {"/provodnik"})
public class MainServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        Enumeration<String> attributeNames=session.getAttributeNames();

        if(!attributeNames.hasMoreElements()){
            resp.sendRedirect(String.format("%s%s", req.getContextPath(),  "/registration"));
        }
        else {

            if(session.getAttribute("user")==null) {
                resp.sendRedirect(String.format("%s%s", req.getContextPath(),  "/registration"));
            }
            else {
                req.setAttribute("isUserLogin", true);
                User user=(User) session.getAttribute("user");
                req.setAttribute("login",user.getName());
            }
        }



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


        if(dir.listFiles()!=null){
            for (File file:dir.listFiles()) {
                if(file.isFile()) files.add(file);
                else directories.add(file);
            }
        }


        req.setAttribute("path", path);
        req.setAttribute("files", files);
        req.setAttribute("directories",directories);



    }
}
