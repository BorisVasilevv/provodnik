package SBapp;

import com.sun.org.apache.xpath.internal.functions.FuncSubstring;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import java.util.List;

@WebServlet("C:/*")
public class JspServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.sendRedirect("/my-app/first-jsp.jsp");
        String str="";
        String uri=req.getRequestURI();

        String path= uri.substring(8);
        File dir = new File(path);
        req.setAttribute("path",path);
        req.setAttribute("files", Arrays.asList(dir.listFiles()));
        req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
