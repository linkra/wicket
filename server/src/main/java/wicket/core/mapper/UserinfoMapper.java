package wicket.core.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import wicket.core.entity.Userinfo;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UserinfoMapper implements ResultSetMapper<Userinfo> {
    @Override
    public Userinfo map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Userinfo(resultSet.getLong("logid"),
                resultSet.getLong("userid"),
                resultSet.getInt("success"),
                resultSet.getString("firstname"),
                resultSet.getString("surname"),
                resultSet.getString("pwd"),
                resultSet.getString("username"),
                resultSet.getTimestamp("attempt")
        );
    }
}
