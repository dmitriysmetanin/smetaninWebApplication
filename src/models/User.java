package models;
import dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class User {
    private String name;
    private String email;
    private String pass;
    public int getId() throws ClassNotFoundException, SQLException {
        UserDao userDao = new UserDao();
        if (userDao.login(this) == 1){
            return userDao.getId(this);
        }
        return 0;
    }

    public ArrayList<Course> getSubscribedCourses() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_SUBSCRIBED_COURSES = String.format("select courses.* from course_user join courses on course_user.course_id = courses.id where user_id = %s;", this.getId());
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_SUBSCRIBED_COURSES,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Course> courses = new ArrayList<>();
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

        }
    }

    public boolean isStudentOf(Course course) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_COURSE_USER_REL = String.format("select count(*) from course_user where (course_id = %s and user_id = %s);", course.getId(), this.getId());
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COURSE_USER_REL,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            int count = Integer.parseInt(rs.getString("count"));
            if (count == 1){return true;}else{return false;}
        }
    }

    public int login() throws ClassNotFoundException, SQLException {
        UserDao userDao = new UserDao();
        return userDao.login(this);
    }


    public String getName () {
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String getPass () {
        return pass;
    }

    public void setPass (String pass){
        this.pass = pass;
    }

}
