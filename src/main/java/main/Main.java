package main;

import Servlets.MainServlet;
import Servlets.RegistrationServlet;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) throws Exception{

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new MainServlet()), "/provodnik");
        ServletHolder s=new ServletHolder(new RegistrationServlet()) ;
        context.addServlet(s, "/registration");
        context.addServlet(s, "/login");


        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});



        Server server= new Server(8080);
        server.setHandler(handlers);
        server.start();
        server.join();
    }

}
