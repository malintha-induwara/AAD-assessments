package lk.ijse.gdse68.jakarthaproject;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(urlPatterns = "/hello",loadOnStartup = 2)
public class HelloServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        System.out.println("Init Method called");
        System.out.println(Thread.currentThread().getName());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Do get Method called");
        System.out.println(Thread.currentThread().getName());
    }

    public void destroy() {
        System.out.println("Destroy Method called");
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DoPost Method called");
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DoDelete Method called");
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DoPut method called");
        System.out.println(Thread.currentThread().getName());
    }
}