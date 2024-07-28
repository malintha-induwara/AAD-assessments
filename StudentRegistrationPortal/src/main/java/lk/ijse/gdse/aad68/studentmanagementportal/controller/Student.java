package lk.ijse.gdse.aad68.studentmanagementportal.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.aad68.studentmanagementportal.bo.StudentBOImpl;
import lk.ijse.gdse.aad68.studentmanagementportal.dao.StudentDAOImpl;
import lk.ijse.gdse.aad68.studentmanagementportal.dto.StudentDTO;
import lk.ijse.gdse.aad68.studentmanagementportal.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

@WebServlet(urlPatterns = "/student",loadOnStartup = 2)
public class Student extends HttpServlet {
    Connection connection;

    static Logger logger = LoggerFactory.getLogger(Student.class);


    @Override
    public void init() throws ServletException {

        logger.info("Init method invoked");

        try {
//            var dbClass = getServletContext().getInitParameter("db-class");
//            var dbUrl = getServletContext().getInitParameter("dburl");
//            var dbUserName = getServletContext().getInitParameter("db-username");
//            var dbPassword = getServletContext().getInitParameter("db-password");
//            Class.forName(dbClass);
//            this.connection = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);

            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/StudentPortal");
            this.connection = pool.getConnection();
            logger.debug("Connection initialized",this.connection);
        }catch ( SQLException | NamingException e){
            logger.error("DB connection not init" );
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            logger.error("Request not matched with the criteria");
        }

        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            var studentBOIMPL = new StudentBOImpl();
            StudentDTO student = jsonb.fromJson(req.getReader(), StudentDTO.class);
            logger.info("Invoke idGenerate()");
            student.setId(Util.idGenerate());
            //Save data in the DB
            writer.write(studentBOIMPL.saveStudent(student,connection));
            logger.info("Student saved successfully");
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }catch (Exception e){
            logger.error("Connection failed");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(var writer = resp.getWriter()) {
            var studentBOIMPL = new StudentBOImpl();
            Jsonb jsonb = JsonbBuilder.create();
            //DB Process
            var studentId = req.getParameter("studentId");;
            resp.setContentType("application/json");
            jsonb.toJson(studentBOIMPL.getStudent(studentId,connection),writer);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (var writer = resp.getWriter()) {
            var studentId = req.getParameter("studentId");
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO student = jsonb.fromJson(req.getReader(), StudentDTO.class);
            var studentBOIMPL = new StudentBOImpl();
            if(studentBOIMPL.updateStudent(studentId,student,connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Update failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(var writer= resp.getWriter()){

            String studentId = req.getParameter("studentId");
            var studentBOIMPL = new StudentBOImpl();
            if(studentBOIMPL.deleteStudent(studentId,connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Delete failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }



}
