package wicket.auth;


import io.dropwizard.auth.Authorizer;
import wicket.core.entity.User;

import java.util.Objects;

public class WicketAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return true;
    }
}
