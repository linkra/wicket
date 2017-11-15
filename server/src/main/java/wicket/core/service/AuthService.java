package wicket.core.service;

import wicket.core.entity.User;
import wicket.core.entity.Userlog;
import wicket.db.jdbi.queries.UserQueries;
import wicket.db.jdbi.update.UserlogUpdate;

public class AuthService {
    private final UserQueries userQueries;
    private final UserlogUpdate userlogUpdate;
    private String message;

    public AuthService(UserQueries userQueries, UserlogUpdate userlogUpdate) {
        this.userQueries = userQueries;
        this.userlogUpdate = userlogUpdate;
    }

    public String sendLoginNoSuccess() {
             return "Sorry, no access";
    }

    public String updateUserlogLoginSuccess(User user) {
        // Find entire user from db
        if (user != null) {
            final User byUsername = getUserByUsername(user);
            Userlog userlog = new Userlog(byUsername.getUserid(), 1);
            userlogUpdate.insert(userlog);
            System.out.println("username in AuthService: " + user.getUsername());
            return String.format("Welcome %s !", user.getUsername());
        }
        return sendLoginNoSuccess();
    }

    public User getUserByUsername(User user) {
        return this.userQueries.findByUsername(user.getUsername());
    }
}
