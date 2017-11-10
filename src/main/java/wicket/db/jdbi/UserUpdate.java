package wicket.db.jdbi;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UserUpdate {
    @SqlUpdate("insert into user (userid, surname) values (:userid, :surname)")
    void insert(@Bind("userid") long userid, @Bind("surname") String surname);
}
