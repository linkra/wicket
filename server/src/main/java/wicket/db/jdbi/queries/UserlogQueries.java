package wicket.db.jdbi.queries;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wicket.core.entity.Userlog;
import wicket.core.mapper.UserlogMapper;

import java.util.List;

@RegisterMapper(UserlogMapper.class)
public interface UserlogQueries {

    @SqlQuery("select * from userlog where userid = :userid and success = '1'")
    List<Userlog> findAllSuccessByUserid(@Bind("userid") String userid);

    @SqlQuery("select * from userlog where userid = :userid and success = '1' order by logid DESC limit 5 offset 0")
    List<Userlog> findLatestSuccessByUserid(@Bind("userid") String userid);
}
