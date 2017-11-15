package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.LongParam;
import wicket.core.entity.Userlog;
import wicket.db.jdbi.queries.UserlogQueries;
import wicket.db.jdbi.update.UserlogUpdate;

import javax.annotation.security.PermitAll;
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
    public List<Userlog> findAllSuccessfulAttemptsByUserid(@PathParam("userid") String userid) {
        return userlogQueries.findAllSuccessByUserid(userid);
    }

    @GET
    @Path("/{userid}/list")
    @Timed
    public List<Userlog> findLatestSuccessfulAttemptsByUserid(@PathParam("userid") String userid) {
        return userlogQueries.findLatestSuccessByUserid(userid);
    }

    @GET
    @Path("/admin/{userid}")       // TODO: add role
    @Timed
    public List<Userlog> findSuccessfulAttemptsByUseridAsAdmin(@PathParam("userid") String userid) {
        return userlogQueries.findAllSuccessByUserid(userid);
    }

    @POST
    @Timed
    public void addUserlog(Userlog userlog) {
        userlogUpdate.insert(userlog);
    }

    
}

