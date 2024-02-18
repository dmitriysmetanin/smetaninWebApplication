package models;

public class SubmittedHometask {
    private int id;
    private int status;
    private String file;
    private String teachersComment;
    private int hometaskId;
    private int userId;

    private int points;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTeachersComment() {
        return teachersComment;
    }

    public void setTeachersComment(String teachersComment) {
        this.teachersComment = teachersComment;
    }

    public int getHometaskId() {
        return hometaskId;
    }

    public void setHometaskId(int hometaskId) {
        this.hometaskId = hometaskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
