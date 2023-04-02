package Servlets;

import accounts.User;
import dao.UsersDataSet;
import jakarta.servlet.http.HttpSession;
import main.DatabaseService;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;




enum interaction{
    registration,
    login,

    logout
}

@WebServlet(urlPatterns = {"/registration", "/login", "/logout"})
public class RegistrationServlet extends HttpServlet {

    final String[] nameOfInteraction=new String[]{"registration","login","logout"};


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String path= req.getRequestURI();

        String[] uri=path.split("/");
        String lastPath=uri[uri.length-1];
        req.getServletContext().getRequestDispatcher("/"+lastPath+".jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        String path= req.getRequestURI();
        String[] uri=path.split("/");
        String lastPath=uri[uri.length-1];
        Map<String,String[]> parameterMap=req.getParameterMap();
        DatabaseService dbService= new DatabaseService();

        if(lastPath.equals(nameOfInteraction[interaction.registration.ordinal()])) {

            String[] check=parameterMap.get("password");
            if(!isPasswordEquals(check[0],check[1],req) ){
                req.getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
            }
            else {
                String name=parameterMap.get("login")[0];
                String password=parameterMap.get("password")[0];
                String email=parameterMap.get("email")[0];
                UsersDataSet set=dbService.getUser(email);




                if (set==null) {
                    User user = new User(name, password, email);
                    dbService.addUser(user);
                    session.setAttribute("user", user);

                    resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/provodnik"));
                }
                else {
                    req.setAttribute("errorMessage","Пользователь с таким email уже зарегистрирован");
                    req.getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
                }
            }
        }
        else if(lastPath.equals(nameOfInteraction[interaction.login.ordinal()])){
            String email=parameterMap.get("email")[0];
            String password=parameterMap.get("password")[0];

            UsersDataSet set=dbService.getUser(email);
            if(set!=null)
            {
                if(set.getPassword().equals(password)){
                    session.setAttribute("user", new User(set.getName(),
                                                             set.getPassword(),
                                                             set.getEmail()));
                    resp.sendRedirect(String.format("%s%s",req.getContextPath(),"/provodnik"));

                }
                else {
                    req.setAttribute("errorMessage", "Неверный пароль для email: "+email);
                    req.getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
                }
            }
            else {
                req.setAttribute("errorMessage", "Пользователь с email: " + email + " не зарегестрирован");
                req.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
        else if(lastPath.equals(nameOfInteraction[interaction.logout.ordinal()])) {

            session.setAttribute("user", null);
            resp.sendRedirect(String.format("%s%s",req.getContextPath(),"/provodnik"));
            session.invalidate();

        }


    }

    private boolean isPasswordEquals(String first, String second, HttpServletRequest req){
        if(first.length()>0){
            if(second.length()>0) {
                if (first.equals(second)) {
                    return true;
                }
                else {
                    req.setAttribute("errorMessage", "Пароли не совпадают попробуйте ещё раз");
                }
            }
            else {
                req.setAttribute("errorMessage", "Поле пароль не может быть пустым");
            }
        }
        else {
            req.setAttribute("errorMessage", "Поле пароль не может быть пустым");
        }

        return false;
    }


}
