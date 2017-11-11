package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import wicket.core.entity.Userlog;
import wicket.db.jdbi.UserlogQueries;
import wicket.db.jdbi.UserlogUpdate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/wicket/userlog")
@Produces(MediaType.APPLICATION_JSON)
public class UserlogResource {
    private final UserlogQueries userlogQueries;
    private final UserlogUpdate userlogUpdate;
    private final AtomicLong counter;  // FIXME!!

    public UserlogResource(UserlogQueries userlogQueries, UserlogUpdate userlogUpdate) {
        this.userlogQueries = userlogQueries;
        this.userlogUpdate = userlogUpdate;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("/{userid}")
    @Timed
    public Userlog findSuccessfulAttemptsByUserid(@PathParam("userid") String userid) {
        return userlogQueries.findSuccessByUserid(userid);
    }

    @POST
    @Timed
    public void addUserlog(Userlog userlog) {
        userlogUpdate.insert(userlog);
    }


}

