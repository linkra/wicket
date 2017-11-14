package wicket.core.entity;

import java.sql.Date;

public class Userlog {
    private Long logid;
    private Long userid;
    private Integer success;
    private Date attempt;

    public Userlog() {
    }

    public Userlog(Long logid, Long userid, Integer success, Date attempt) {
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

    public Date getAttempt() {
        return attempt;
    }

    public void setAttempt(Date attempt) {
        this.attempt = attempt;
    }
}
