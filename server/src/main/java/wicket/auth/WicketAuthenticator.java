package wicket.auth;



import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import wicket.core.entity.User;

import java.util.Optional;

public class WicketAuthenticator implements Authenticator<String, User> {
    @Override
    public Optional<User> authenticate(String token) throws AuthenticationException {
        if ("test_token".equals(token)) {
            return Optional.of(new User());
        }
        return Optional.empty();
    }
}
