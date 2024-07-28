package lk.ijse.gdse.aad68.studentmanagementportal.bo;

import lk.ijse.gdse.aad68.studentmanagementportal.dao.StudentDAOImpl;
import lk.ijse.gdse.aad68.studentmanagementportal.dto.StudentDTO;

import java.sql.Connection;

public class StudentBOImpl implements StudentBO{

    @Override
    public String saveStudent(StudentDTO student, Connection connection) throws Exception {
        var studentDAOIMPL = new StudentDAOImpl();
        return studentDAOIMPL.saveStudent(student, connection);
    }

    @Override
    public boolean deleteStudent(String id, Connection connection) throws Exception {
        var studentDAOIMPL = new StudentDAOImpl();
        return studentDAOIMPL.deleteStudent(id, connection);
    }

    @Override
    public boolean updateStudent(String id, StudentDTO student, Connection connection) throws Exception {
        var studentDAOIMPL = new StudentDAOImpl();
        return studentDAOIMPL.updateStudent(id, student, connection);
    }

    @Override
    public StudentDTO getStudent(String id, Connection connection) throws Exception {
        var studentDAOIMPL = new StudentDAOImpl();
        return studentDAOIMPL.getStudent(id, connection);
    }
}

