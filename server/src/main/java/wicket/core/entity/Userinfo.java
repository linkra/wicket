package wicket.core.entity;

import java.sql.Date;

public class Userinfo {
    private User user;
    private Userlog userlog;

    public Userinfo() {
    }

    public Userinfo(Long logid, Long userid, Integer success, String firstname, String surname, String pwd, String username, Date attempt) {
        User user = new User();
        user.setFirstname(firstname);
        user.setSurname(surname);
        user.setPwd(pwd);
        user.setUsername(username);
        this.user = user;

        Userlog userlog = new Userlog();
        userlog.setLogid(logid);
        userlog.setSuccess(success);
        userlog.setUserid(userid);
        this.userlog = userlog;
    }

    public Userinfo(User user, Userlog userlog) {
        this.user = user;
        this.userlog = userlog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Userlog getUserlog() {
        return userlog;
    }

    public void setUserlog(Userlog userlog) {
        this.userlog = userlog;
    }
}
