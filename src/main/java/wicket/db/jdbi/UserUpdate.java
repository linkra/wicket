package wicket.db.jdbi;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import wicket.core.entity.User;

public interface UserUpdate {

    @SqlUpdate("insert into user (userid, firstname, surname, address, email, pwd, username) values (:userid, :firstname, :surname, :address, :email, :pwd, :username)")
    User insert(@BindBean User u);
}
