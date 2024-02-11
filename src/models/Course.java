package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Course {
    private int id;
    private String name;
    private String description;
    private int authorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }



    public ArrayList<Module> getModules() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String SELECT_SUBSCRIBED_COURSES = String.format("select modules.* from modules where courseid = %s ;", this.getId());
        String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "root");
        props.setProperty("ssl", "false");

        try (Connection conn = DriverManager.getConnection(url, props);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_SUBSCRIBED_COURSES,
                     ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Module> modules = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
//                String content = result.getString("content");
                int courseid = result.getInt("courseid");

                Module module = new Module();
                module.setId(id);
                module.setName(name);
                module.setDesctiption(description);
//                module.setContent(content);
                module.setCourseid(courseid);
                modules.add(module);
            }

            return modules;
        }
    }
}
