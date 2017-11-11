package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import wicket.core.entity.Userinfo;
import wicket.db.jdbi.queries.UserinfoQueries;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/wicket/userinfo")
@Produces(MediaType.APPLICATION_JSON)
public class UserinfoResource {

    private final UserinfoQueries userinfoQueries;
    private final AtomicLong counter;  // FIXME!!

    public UserinfoResource(UserinfoQueries userinfoQueries) {
        this.userinfoQueries = userinfoQueries;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("/{userid}")
    @Timed
    public List<Userinfo> findSuccessfulAttemptsByUserid(@PathParam("userid") String userid) {
        return userinfoQueries.findSuccessfulUserinfoByUserid(userid);
    }
}
