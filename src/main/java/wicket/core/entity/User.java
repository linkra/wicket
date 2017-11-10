package wicket.core.entity;




import javax.persistence.*;

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name = "wicket.core.entity.User.findAll",
                query = "select u from user u"),
        @NamedQuery(name = "wicket.core.entity.User.findBySurname",
                query = "select n from user n "
                        + "where n.surname like :surname ")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "username")
    private String username;

    public User() {
    }

    public User(String firstname, String surname, String address, String email, String pwd, String username) {
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
