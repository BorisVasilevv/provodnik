package SBapp;

import com.sun.org.apache.xpath.internal.functions.FuncSubstring;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

@WebServlet(urlPatterns = {"/", "/*"})
public class JspServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path= req.getParameter("path");
        if(path==null||path=="") {
            path = System.getProperty("user.home");
            String wayNow="/my-app/"+ path;
            resp.sendRedirect(wayNow);
        }

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
        req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}
