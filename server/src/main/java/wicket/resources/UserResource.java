package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.LongParam;
import wicket.core.entity.User;
import wicket.core.entity.Userlog;
import wicket.core.service.AuthService;
import wicket.db.jdbi.queries.UserQueries;
import wicket.db.jdbi.update.UserUpdate;
import wicket.db.jdbi.update.UserlogUpdate;
import wicket.views.UserView;


import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Path("rest/v0/wicket/user")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
public class UserResource {
    private final UserQueries userQueries;
    private final UserUpdate userUpdate;
    private final UserlogUpdate userlogUpdate;
    private final AtomicLong counter;
    private AuthService authService;

    public UserResource(UserQueries userQueries, UserUpdate userUpdate, UserlogUpdate userlogUpdate) {
        this.userQueries = userQueries;
        this.userUpdate = userUpdate;
        this.userlogUpdate = userlogUpdate;
        this.counter = new AtomicLong();
        authService = new AuthService(userQueries, userlogUpdate);
    }

    @PermitAll
    @GET
    @Path("/surname/s/w/{surname}")
    @Timed
    public User findUserBySurname(@PathParam("surname") String surname) {
        return userQueries.findBySurname(surname);
    }

    @PermitAll
    @GET
    @Path("/{userid}")
    @Timed
    public User findUserByUserid(@PathParam("userid") LongParam userid) {
        return userQueries.findByUserid(userid.get());
    }

    @PermitAll
    @GET
    @Timed
    public List<User> findAllUsers() {
        return userQueries.findAll();
    }

    @PermitAll
    @GET
    @Path("/username/{username}")
    @Timed
    public User findUserByUsername(@PathParam("username") String username) {
        return userQueries.findByUsername(username);
    }

    @POST
    @Path("/register")
    @Timed
    public void addUser(User user) {
        userUpdate.insert(user);
    }

    @PermitAll
    @POST
    @Path("/login")
    @Timed
    public String login(User bodyuser) {
        // user object is returned here from WicketAuthenticator if it's authorized, otherwise null
       if (bodyuser != null && bodyuser.getUsername() != null) {
            System.out.println("Login attempt by: " + bodyuser.getUsername());
        } else {
            System.out.println("Login attempt by someone without username");
        }
        return authService.updateUserlogLoginSuccess(bodyuser);
        
    }

    @PermitAll
    @GET
    @Path("/template/w/{userid}")     // for mustache template, not in use yet
    @Timed
    public UserView getUser(@PathParam("userid") LongParam userid) {
        return new UserView(userQueries.findByUserid(userid.get()));
    }

}
