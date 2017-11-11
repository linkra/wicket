package wicket.db.jdbi;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wicket.core.entity.User;
import wicket.core.mapper.UserMapper;

@RegisterMapper(UserMapper.class)
public interface UserUpdate {

    @SqlUpdate("insert into user (userid, firstname, surname, address, email, pwd, username) values (:userid, :firstname, :surname, :address, :email, :pwd, :username)")
    void insert(@BindBean User u);
}
