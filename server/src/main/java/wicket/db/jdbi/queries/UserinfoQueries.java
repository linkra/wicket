package wicket.db.jdbi.queries;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wicket.core.entity.Userinfo;
import wicket.core.mapper.UserinfoMapper;

import java.util.List;

@RegisterMapper(UserinfoMapper.class)
public interface UserinfoQueries {
    @SqlQuery("select * from userlog as ul, user as u where u.userid = :userid and ul.success = '1' and u.userid = ul.userid")
    List<Userinfo> findSuccessfulUserinfoByUserid(@Bind("userid") String userid);
}

