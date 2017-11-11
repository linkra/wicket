package wicket.db.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wicket.core.entity.Userlog;
import wicket.core.mapper.UserlogMapper;

@RegisterMapper(UserlogMapper.class)
public interface UserlogQueries {

    @SqlQuery("select * from userlog where userid = :userid and where success = 1")
    Userlog findSuccessByUserid(@Bind("userid") String userid);
}
