package dao;

import models.Hometask;
import models.SubmittedHometask;

import java.sql.*;
import java.util.Properties;

public class SubmittedHometaskDao {
    // -1 - ДЗ для модуля не существует
    // 0 - ДЗ не сдано
    // 1 - ДЗ сдано
    // 2 - ДЗ на проверке
    // 3 - ДЗ проверено

    public SubmittedHometask getSubmittedHometask(int module_id, int user_id) throws SQLException, ClassNotFoundException {
        HometaskDao hometaskDao = new HometaskDao();

        if (hometaskDao.existsForModuleByModuleId(module_id)) {
            Hometask hometask = hometaskDao.getHometaskByModuleId(module_id);
            // Проверяем, есть ли в базе сданная домашка с таким hometask_id и user_id
            Class.forName("org.postgresql.Driver");
            String SELECT_SUBMITTED_HOMETASKS = String.format("select * from submittedhometasks where hometaskid = '%s' and userid = '%s'", hometask.getId(), user_id);
            String url = "jdbc:postgresql://localhost:5432/smetaninWebApplicationDatabase";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "root");
            props.setProperty("ssl", "false");
            try (Connection conn = DriverManager.getConnection(url, props);
                 PreparedStatement preparedStatement = conn.prepareStatement(SELECT_SUBMITTED_HOMETASKS,
                         ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                ResultSet result = preparedStatement.executeQuery();
                result.first();
                int row_count = result.getRow();
                if (row_count == 1) {
                    int id = result.getInt("id");
                    int status = result.getInt("status");
                    String file = result.getString("file");
                    String teachersComment = result.getString("teachersComment");
                    int hometaskId = result.getInt("hometaskId");
                    int userId = result.getInt("userId");
                    int points = result.getInt("points");
                    SubmittedHometask submittedHometask = new SubmittedHometask();
                    submittedHometask.setId(id);
                    submittedHometask.setStatus(status);
                    submittedHometask.setPoints(points);
                    submittedHometask.setFile(file);
                    submittedHometask.setTeachersComment(teachersComment);
                    submittedHometask.setHometaskId(hometaskId);
                    submittedHometask.setUserId(userId);

                    return submittedHometask;
                }
            }
        }
        return null;
    }

    public int getStatus(int module_id, int user_id) throws SQLException, ClassNotFoundException {
        int status = -1;
        HometaskDao hometaskDao = new HometaskDao();
        if (hometaskDao.existsForModuleByModuleId(module_id)) {
            status = 0;
            SubmittedHometask submittedHometask = this.getSubmittedHometask(module_id, user_id);
            if (submittedHometask != null) {
                status = submittedHometask.getStatus();
            }

        }
        return status;
    }
}



