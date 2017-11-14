package wicket.core.entity;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Userlog {
    private Long logid;
    private Long userid;
    private Integer success;
    private Timestamp attempt;

    public Userlog() {
    }

    public Userlog(Long userid, Integer success) {
        this.logid = logid;
        this.userid = userid;
        this.success = success;
        this.attempt = attempt;
    }

    public Userlog(Long logid, Long userid, Integer success, Timestamp attempt) {
        this.logid = logid;
        this.userid = userid;
        this.success = success;
        this.attempt = attempt;
    }

    public Long getLogid() {
        return logid;
    }

    public void setLogid(Long logid) {
        this.logid = logid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Timestamp getAttempt() {
        return attempt;
    }

    public void setAttempt(Timestamp attempt) {
        this.attempt = attempt;
    }

    public String getFormattedTimestamp() {
        if (attempt == null) {
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            attempt = new Timestamp(now.getTime());
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(attempt);
    }

}
