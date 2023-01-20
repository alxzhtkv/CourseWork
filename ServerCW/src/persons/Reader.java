package persons;

import java.io.Serializable;

public class Reader extends User implements Serializable {
    private String name;
    private String surname;
    private String patronymic;
    private String passportID;
    private String phone;
    private String birthDay;


    public Reader(String login, String password, String name, String surname, String patronymic, String passportID, String phone, String birthDay) {
        super(login, password);
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.passportID = passportID;
        this.phone = phone;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passwordID) {
        this.passportID = passwordID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}