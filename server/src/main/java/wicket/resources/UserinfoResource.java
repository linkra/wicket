package wicket.resources;


import com.codahale.metrics.annotation.Timed;
import wicket.core.entity.Userinfo;
import wicket.db.jdbi.queries.UserinfoQueries;


import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@PermitAll
@Path("rest/v0/wicket/userinfo")
@Produces(MediaType.APPLICATION_JSON)
public class UserinfoResource {

    private final UserinfoQueries userinfoQueries;

    public UserinfoResource(UserinfoQueries userinfoQueries) {
        this.userinfoQueries = userinfoQueries;
    }

    @GET
    @Path("/{userid}")
    @Timed
    public List<Userinfo> findSuccessfulAttemptsByUserid(@PathParam("userid") String userid) {
        return userinfoQueries.findSuccessfulUserinfoByUserid(userid);
    }
}
