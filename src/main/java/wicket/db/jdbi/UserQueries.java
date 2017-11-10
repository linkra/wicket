package wicket.db.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wicket.core.entity.User;
import wicket.core.mapper.UserMapper;

@RegisterMapper(UserMapper.class)
public interface UserQueries {

    @SqlQuery("select * from user where userid = :userid")
    User findByUserid(@Bind("userid") int userid);

    @SqlQuery("select * from user where surname = :surname")
    User findBySurname(@Bind("surname") String surname);
    
}
