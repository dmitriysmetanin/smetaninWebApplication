package models;

import dao.HometaskDao;
import dao.ModuleDao;

import java.sql.SQLException;

public class Module {

    public Hometask getHometask() throws SQLException, ClassNotFoundException {
        HometaskDao hometaskDao = new HometaskDao();
        return hometaskDao.getHometaskByModuleId(this.getId());
    }

    private int id;
    private String name;
    private String desctiption;
    private String content;
    private int courseid;

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

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }
}
