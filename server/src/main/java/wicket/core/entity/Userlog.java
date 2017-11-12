package wicket.core.entity;

public class Userlog {
    private Long logid;
    private Long userid;
    private Integer success;

    public Userlog() {
    }

    public Userlog(Long logid, Long userid, Integer success) {
        this.logid = logid;
        this.userid = userid;
        this.success = success;
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
}
