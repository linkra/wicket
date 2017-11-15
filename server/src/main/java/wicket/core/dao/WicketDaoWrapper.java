package wicket.core.dao;

import wicket.db.jdbi.queries.UserQueries;
import wicket.db.jdbi.queries.UserinfoQueries;
import wicket.db.jdbi.queries.UserlogQueries;
import wicket.db.jdbi.update.UserUpdate;
import wicket.db.jdbi.update.UserlogUpdate;

public class WicketDaoWrapper {

    private UserQueries userQueries;
    private UserUpdate userUpdate;
    private UserlogQueries userlogQueries;
    private UserlogUpdate userlogUpdate;
    private UserinfoQueries userinfoQueries;

    public WicketDaoWrapper() {
    }

    public WicketDaoWrapper(UserQueries userQueries, UserUpdate userUpdate, UserlogQueries userlogQueries,
                            UserlogUpdate userlogUpdate, UserinfoQueries userinfoQueries) {
        this.userQueries = userQueries;
        this.userUpdate = userUpdate;
        this.userlogQueries = userlogQueries;
        this.userlogUpdate = userlogUpdate;
        this.userinfoQueries = userinfoQueries;
    }

    public UserQueries getUserQueries() {
        return userQueries;
    }

    public void setUserQueries(UserQueries userQueries) {
        this.userQueries = userQueries;
    }

    public UserUpdate getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(UserUpdate userUpdate) {
        this.userUpdate = userUpdate;
    }

    public UserlogQueries getUserlogQueries() {
        return userlogQueries;
    }

    public void setUserlogQueries(UserlogQueries userlogQueries) {
        this.userlogQueries = userlogQueries;
    }

    public UserlogUpdate getUserlogUpdate() {
        return userlogUpdate;
    }

    public void setUserlogUpdate(UserlogUpdate userlogUpdate) {
        this.userlogUpdate = userlogUpdate;
    }

    public UserinfoQueries getUserinfoQueries() {
        return userinfoQueries;
    }

    public void setUserinfoQueries(UserinfoQueries userinfoQueries) {
        this.userinfoQueries = userinfoQueries;
    }
}
