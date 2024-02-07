package com.example.smetaninwebapplication.registration.model;
import com.example.smetaninwebapplication.registration.dao.UserDao;
import com.example.smetaninwebapplication.studying.model.Course;

import java.sql.SQLException;

public class User {
    private String name;
    private String email;
    private String pass;

    public Boolean isStudentOf(Course course){
        return true;
    }
    public int getId() throws ClassNotFoundException, SQLException {
        UserDao userDao = new UserDao();
        if (userDao.login(this) == 1){
            return userDao.getId(this);
        }
        return 0;
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
