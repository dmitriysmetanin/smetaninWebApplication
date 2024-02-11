package dao;

import models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CourseDao {

    public boolean exists(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_COURSE = String.format("select count(*) from courses where id = %s", id);
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COURSE,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet result = preparedStatement.executeQuery();
            result.first();
            int count = result.getInt("count");
            if (count == 1){return true;} else {return false;}
        }
    }


    public Course getById(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_COURSE = String.format("select * from courses where (id = %s) LIMIT 1;", id);
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COURSE,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            Course course = new Course();
            course.setId(Integer.parseInt(rs.getString("id")));
            course.setName(rs.getString("name"));
            course.setDescription(rs.getString("description"));
            course.setAuthorId(Integer.parseInt(rs.getString("authorid")));

            return course;
        }
    }
    public ArrayList<Course> getAllCourses() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String INSERT_USERS = "SELECT * from courses;";
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USERS,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY)) {
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getEmail());
//            preparedStatement.setString(3, user.getPass());
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Course> courses = new ArrayList<Course>();
            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                Integer authorId = result.getInt("authorId");

                Course course = new Course();
                course.setId(id);
                course.setName(name);
                course.setDescription(description);
                course.setAuthorId(authorId);
                courses.add(course);
            }
            return courses;

        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
