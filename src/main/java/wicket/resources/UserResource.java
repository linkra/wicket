package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.LongParam;
import wicket.core.entity.User;
import wicket.db.jdbi.queries.UserQueries;
import wicket.db.jdbi.update.UserUpdate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/wicket/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserQueries userQueries;
    private final UserUpdate userUpdate;
    private final AtomicLong counter;  // FIXME!!

    public UserResource(UserQueries userQueries, UserUpdate userUpdate) {
        this.userQueries = userQueries;
        this.userUpdate = userUpdate;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("/surname/{surname}")
    @Timed
    public User findUserBySurname(@PathParam("surname") String surname) {
        return userQueries.findBySurname(surname);
    }

    @GET
    @Path("/{userid}")
    @Timed
    public User findUserByUserid(@PathParam("userid") LongParam userid) {
        return userQueries.findByUserid(userid.get());
    }

    @GET
    @Timed
    public List<User> findAllUsers() {
        return userQueries.findAll();
    }

    @GET
    @Path("/username/{username}")
    @Timed
    public User findUserByUsername(@PathParam("username") String username) {
        return userQueries.findByUsername(username);
    }

    @POST
    @Timed
    public void addUser(User user) {
        userUpdate.insert(user);
    }

    
}
