package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.LongParam;
import wicket.core.entity.User;
import wicket.core.entity.Userlog;
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

    public UserResource(UserQueries userQueries, UserUpdate userUpdate, UserlogUpdate userlogUpdate) {
        this.userQueries = userQueries;
        this.userUpdate = userUpdate;
        this.userlogUpdate = userlogUpdate;
        this.counter = new AtomicLong();
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
    public String login(User user) {
        System.out.println("Login attempt by: " + user);  // TODO: loggning
        String msg = "Sorry, no access";
        // TODO: put in a service class
        if (user != null) {
            final User byUsername = this.userQueries.findByUsername(user.getUsername());
            Userlog userlog = new Userlog(byUsername.getUserid(), 1);
            userlogUpdate.insert(userlog);
            msg = String.format("Welcome %s !", user.getUsername());
        }
        return msg;
        
    }

    @PermitAll
    @GET
    @Path("/template/w/{userid}")
    @Timed
    public UserView getUser(@PathParam("userid") LongParam userid) {
        return new UserView(userQueries.findByUserid(userid.get()));
    }

}
