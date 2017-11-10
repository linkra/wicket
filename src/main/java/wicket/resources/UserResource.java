package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import wicket.core.entity.User;
import wicket.db.jdbi.UserQueries;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/wicket/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserQueries dao;
    private final AtomicLong counter;

    public UserResource(UserQueries dao) {
        this.dao = dao;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("/{surname}")
    @Timed
    @UnitOfWork
    public User findUserBySurname(@PathParam("surname") Optional<String> surname) {
        // mappa till DTO?
        if (surname.isPresent()) {
            return dao.findBySurname(surname.get());
        } else {
            return new User("inget");
        }

    }
}
