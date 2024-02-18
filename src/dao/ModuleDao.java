package dao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Hometask;
import models.Module;

import java.sql.*;
import java.util.Properties;

public class ModuleDao {
    public Hometask getHometask(int module_id) throws SQLException, ClassNotFoundException {
        if (this.hometaskExists(module_id)){
            Class.forName("org.postgresql.Driver");
            String SELECT_HOMETASK = String.format("select * from hometasks where moduleid = '%s'", module_id);
            String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "root");
            props.setProperty("ssl", "false");
            try (Connection conn = DriverManager.getConnection(url, props);
                 PreparedStatement preparedStatement = conn.prepareStatement(SELECT_HOMETASK,
                         ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                ResultSet result = preparedStatement.executeQuery();
                result.first();

                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                String content = result.getString("content");
                int maxPoints = result.getInt("maxPoints");
                int moduleId = result.getInt("moduleId");

                Hometask hometask = new Hometask();
                hometask.setId(id);
                hometask.setName(name);
                hometask.setDescription(description);
                hometask.setContent(content);
                hometask.setMaxPoints(maxPoints);
                hometask.setModuleId(moduleId);

                return hometask;
            }
        } else {
            return null;
        }
    }

    public boolean hometaskExists(int module_id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_MODULE = String.format("select count(*) from hometasks where moduleid = '%s'", module_id);
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
