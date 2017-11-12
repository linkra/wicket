package wicket.core.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import wicket.core.entity.Userlog;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserlogMapper implements ResultSetMapper<Userlog> {
    @Override
    public Userlog map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Userlog(resultSet.getLong("logid"),
                resultSet.getLong("userid"),
                resultSet.getInt("success")
        );
    }
}

