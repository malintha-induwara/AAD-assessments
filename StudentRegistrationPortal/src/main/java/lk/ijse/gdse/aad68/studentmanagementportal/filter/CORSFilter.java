package lk.ijse.gdse.aad68.studentmanagementportal.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter(urlPatterns = "/*")
public class CORSFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {


        var origin = getServletContext().getInitParameter("origin");

        //To allow only specific origin 5050
        res.setHeader("Access-Control-Allow-Origin",origin);

        //Allow methods
        res.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,PATCH");
        res.setHeader("Access-Control-Allow-Headers","Content-Type");
    }
}

