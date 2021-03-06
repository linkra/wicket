package wicket.views;

import io.dropwizard.views.View;
import wicket.core.entity.User;

public class UserView extends View {
    private final User user;
    private final String firstname;
    private final String surname;

    public UserView(User user) {
        super("server/src/main/resources/views/user.mustache");
        this.user = user;
        this.firstname = user.getFirstname();
        this.surname = user.getSurname();
    }

    public User getUser() {
        return user;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getViewClass() {
        return UserView.class.getName();
    }

}
