package dao;

import models.Module;

import java.sql.*;
import java.util.Properties;

public class ModuleDao {
    public boolean exists(int course_id, int module_id) throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        String SELECT_MODULE = String.format("select count(*) from modules where (id = %s and courseid = %s)", module_id, course_id);
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");
        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_MODULE,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet result = preparedStatement.executeQuery();
            result.first();
            int count = result.getInt("count");
            if (count == 1) {return true;} else {return false;}
        }
    }

    public Module getById(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_COURSE = String.format("select * from modules where (id = %s) LIMIT 1;", id);
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
            Module module = new Module();
            module.setId(rs.getInt("id"));
            module.setName(rs.getString("name"));
            module.setDesctiption(rs.getString("description"));
            module.setContent(rs.getString("content"));
            module.setCourseid(rs.getInt("courseid"));

            return module;
        }
    }

}
