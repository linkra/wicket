package wicket.core.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import wicket.core.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new User(resultSet.getLong("userid"),
                resultSet.getString("firstname"),
                resultSet.getString("surname"),
                resultSet.getString("address"),
                resultSet.getString("email"),
                resultSet.getString("pwd"),
                resultSet.getString("username")
                );
    }
}
