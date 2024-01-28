package services;
import java.util.Date;

public class GetDateClass {
    public String getCurrentDate(){
        Date date = new Date();
        return date.toString();
    }
}
