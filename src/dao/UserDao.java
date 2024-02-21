package dao;

import models.User;
import models.Course;

import java.sql.*;
import java.util.Properties;

public class UserDao {
    public boolean exists(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_COURSE = String.format("select count(*) from users where id = %s", id);
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
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public User getById(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_COURSE = String.format("select * from users where (id = %s) LIMIT 1;", id);
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COURSE,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            User user = new User();
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPass(rs.getString("pass"));
            user.setPhone(rs.getString("phone"));
            user.setAbout(rs.getString("about"));

            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Integer subscribe(Course course, User user) throws ClassNotFoundException {
        int result = 0;
        Class.forName("org.postgresql.Driver");
        String INSERT_USERS = "INSERT INTO course_user (course_id, user_id) values (?, ?);";
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props); PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USERS)) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setInt(2, user.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }

    public String getNameById(int id) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String GET_USER_NAME = String.format("select name from users where (id = %s) LIMIT 1;", id);
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_NAME,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            return rs.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId(User user) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_USER = String.format("SELECT id FROM users WHERE (email = '%s') and (pass = '%s') limit 1;", user.getEmail(), user.getPass());
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            return rs.getInt(1);
        }
    }

    public int login(User user) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String SELECT_USER = String.format("SELECT count(name) as count FROM users WHERE (email = '%s') and (pass = '%s') group by name;", user.getEmail(), user.getPass());
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";

        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            int row_count = rs.getRow();
            return row_count;

        } catch (SQLException ex) {
            System.out.println("Ошибка в UserDao login");
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int registerUser(User user) throws ClassNotFoundException {
        int result = 0;
        Class.forName("org.postgresql.Driver");
        String INSERT_USERS = "INSERT INTO users (name, email, pass) values (?, ?, ?);";
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props); PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USERS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPass());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
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
