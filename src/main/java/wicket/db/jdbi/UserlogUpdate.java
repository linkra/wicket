package wicket.db.jdbi;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wicket.core.entity.Userlog;
import wicket.core.mapper.UserlogMapper;

@RegisterMapper(UserlogMapper.class)
public interface UserlogUpdate {

    @SqlUpdate("insert into userlog (userid, success) values (:userid, :success)")
    void insert(@BindBean Userlog ul);
}
