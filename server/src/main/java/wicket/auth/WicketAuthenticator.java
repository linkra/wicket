package wicket.auth;



import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import wicket.core.entity.User;
import wicket.db.jdbi.queries.UserQueries;

import java.util.Optional;

public class WicketAuthenticator implements Authenticator<BasicCredentials, User> {
    private final UserQueries userQueries;

    /* Inject UserQueries in WicketApplication.run */
    public WicketAuthenticator(UserQueries userQueries) {
        this.userQueries = userQueries;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        User user = this.userQueries.findByUsername(credentials.getUsername());
        if (user != null && user.getPwd().equals(credentials.getPassword())) {
            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.empty();
    }



}
