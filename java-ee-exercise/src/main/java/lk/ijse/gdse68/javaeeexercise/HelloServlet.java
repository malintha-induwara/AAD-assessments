package lk.ijse.gdse68.javaeeexercise;

import java.io.*;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


public class HelloServlet extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Hello Servlet: ini (Servlet Config)");
        super.init(config);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello Servlet : doGet");
        super.doGet(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello Servlet: service");
        super.service(req, resp);
    }

    public HelloServlet(){
        System.out.println("Hello Servlet: constructor");
    }

    {
        System.out.println("Hello Servlet : instance");
    }

    static {
        System.out.println("Hello Servlet: static");
    }


}