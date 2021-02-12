package ipoteka_calculator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private static Customer customer = null;
    private long id;
    private String name;
    private String surname;
    private String birthday;
    private String cinsiyyet;
    private long age;

    public Customer() {
    }

   public static Customer instance(){
        if (customer == null){
            customer = new Customer();
        }
        return customer;
   }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCinsiyyet() {
        return cinsiyyet;
    }

    public void setCinsiyyet(String cinsiyyet) {
        this.cinsiyyet = cinsiyyet;
    }

    public long getAge() {
        try {
            Date birthDayDate;
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd.MM.yyyy");
            birthDayDate = formatter.parse(birthday);
            Date currentDate = new Date();
            long diffInMillis = Math.abs(currentDate.getTime() - birthDayDate.getTime());
            age = diffInMillis / (1000L * 60 * 60 * 24 * 365);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return age;
    }

}
