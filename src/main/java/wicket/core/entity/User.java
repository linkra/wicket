package wicket.core.entity;

public class User {

    private Long userid;

    private String firstname;

    private String surname;

    private String address;

    private String email;

    private String pwd;

    private String username;

    public User() {
    }

    public User(String surname) {
        this.surname = "No user found";
    }

    public User(Long userid, String surname) {
        this.userid = userid;
        this.surname = surname;
    }

    public User(Long userid, String firstname, String surname, String pwd, String username) {
        this.userid = userid;
        this.firstname = firstname;
        this.surname = surname;
        this.pwd = pwd;
        this.username = username;
    }

    public User(Long userid, String firstname, String surname, String address, String email, String pwd, String username) {
        this.userid = userid;
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.pwd = pwd;
        this.username = username;
    }


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
