package Servlets;



import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.*;
import java.util.Arrays;

@WebServlet("/download")
public class SaveServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String item = req.getParameter("path");
        String filename= Arrays.stream(item.split("\\\\")).reduce((e1,e2)->e2).orElse(null);
        if(item!=null){
            resp.setHeader("Content-Disposition", "attachment; filename="+ filename);
            try (InputStream inputStream = new FileInputStream(item); OutputStream outputStream = resp.getOutputStream()) {
                byte[] buffer = new byte[1048];

                int numBytesRead;
                while ((numBytesRead = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, numBytesRead);
                }
            } catch (FileNotFoundException e) {
                resp.sendError(404);
            }
        } else {
            resp.sendError(404);
        }
    }
}
