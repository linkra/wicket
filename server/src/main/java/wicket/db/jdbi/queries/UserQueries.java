package wicket.db.jdbi.queries;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import wicket.core.entity.User;
import wicket.core.mapper.UserMapper;

import java.util.List;

@RegisterMapper(UserMapper.class)
public interface UserQueries {

    @SqlQuery("select * from user where userid = :userid")
    User findByUserid(@Bind("userid") long userid);

    @SqlQuery("select * from user where surname = :surname")
    User findBySurname(@Bind("surname") String surname);

    @SqlQuery("select * from user where username = :username")
    User findByUsername(@Bind("username") String username);

    @SqlQuery("select * from user")
    List<User> findAll();

}
