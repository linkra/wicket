package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import wicket.core.entity.User;
import wicket.core.entity.Userlog;
import wicket.db.jdbi.queries.UserlogQueries;
import wicket.db.jdbi.update.UserlogUpdate;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@PermitAll
@Path("rest/v0/wicket/userlog")
@Produces(MediaType.APPLICATION_JSON)
public class UserlogResource {
    private final UserlogQueries userlogQueries;
    private final UserlogUpdate userlogUpdate;
    private final AtomicLong counter;

    public UserlogResource(UserlogQueries userlogQueries, UserlogUpdate userlogUpdate) {
        this.userlogQueries = userlogQueries;
        this.userlogUpdate = userlogUpdate;
        this.counter = new AtomicLong();
    }


    @GET
    @Path("/{userid}")
    @Timed
    public List<Userlog> findSuccessfulAttemptsByUserid(@PathParam("userid") String userid) {
        return userlogQueries.findSuccessByUserid(userid);
    }

    @GET
    @Path("/admin/{userid}")       // TODO: add role
    @Timed
    public List<Userlog> findSuccessfulAttemptsByUseridAsAdmin(@PathParam("userid") String userid) {
        return userlogQueries.findSuccessByUserid(userid);
    }

    @POST
    @Timed
    public void addUserlog(Userlog userlog) {
        userlogUpdate.insert(userlog);
    }


}

