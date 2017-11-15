package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.LongParam;
import wicket.core.entity.User;
import wicket.core.entity.Userlog;
import wicket.core.service.AuthService;
import wicket.db.jdbi.queries.UserQueries;
import wicket.db.jdbi.queries.UserlogQueries;
import wicket.db.jdbi.update.UserlogUpdate;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@PermitAll
@Path("rest/v0/wicket/userlog")
@Produces(MediaType.APPLICATION_JSON)
public class UserlogResource {
    private final UserlogQueries userlogQueries;
    private final UserlogUpdate userlogUpdate;
    private final AtomicLong counter;
    private AuthService authService;

    public UserlogResource(UserQueries userQueries, UserlogQueries userlogQueries, UserlogUpdate userlogUpdate) {
        this.userlogQueries = userlogQueries;
        this.userlogUpdate = userlogUpdate;
        this.counter = new AtomicLong();
        authService = new AuthService(userQueries, userlogUpdate);
    }

    @GET
    @Path("/{userid}")
    @Timed
    public List<Userlog> findAllSuccessfulAttemptsByUserid(@PathParam("userid") String userid) {
        return userlogQueries.findAllSuccessByUserid(userid);
    }

    @POST
    @Path("/{userid}/list")
    @Timed
    public List<Userlog> findOwnLatestSuccessfulAttemptsByUseridAndUsername(@PathParam("userid") String userid, User bodyuser) {
        // Get entire permitted user object
        User userByUsername = authService.getUserByUsername(bodyuser);
        if (userByUsername != null && userByUsername.getUserid() != null
                && userByUsername.getUserid().equals(Long.valueOf(userid))) {
            return userlogQueries.findLatestSuccessByUserid(userid);
        }
        return Collections.emptyList();
    }

    @GET
    @Path("/admin/{userid}")       // TODO: add role   and @Auth User user
    @Timed
    public List<Userlog> findSuccessfulAttemptsByUseridAsAdmin(@PathParam("userid") String userid) {
        return userlogQueries.findAllSuccessByUserid(userid);
    }

    @POST
    @Timed
    public void addUserlog(Userlog userlog) {
        // used for testing
        userlogUpdate.insert(userlog);
    }
}

