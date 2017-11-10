package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import wicket.core.entity.User;
import wicket.db.jdbi.UserQueries;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/wicket/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserQueries dao;
    private final AtomicLong counter;  // FIXME!!

    public UserResource(UserQueries dao) {
        this.dao = dao;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("/surname/{surname}")
    @Timed
    @UnitOfWork
    public User findUserBySurname(@PathParam("surname") Optional<String> surname) {
        // mappa till DTO?
        return dao.findBySurname(surname.orElse("Nope"));

    }

    @GET
    @Path("/{userid}")
    @Timed
    @UnitOfWork
    public User findUserByUserid(@PathParam("userid") LongParam userid) {
        // mappa till DTO?
        return dao.findByUserid(userid.get());

    }

    @GET
    @Timed
    @UnitOfWork
    public List<User> findAllUsers() {
        // mappa till DTO?
        return dao.findAll();

    }

    @GET
    @Path("/username/{username}")
    @Timed
    @UnitOfWork
    public User findUserByUsername(@PathParam("username") Optional<String> username) {
        // mappa till DTO?
        return dao.findByUsername(username.orElse("Nope"));

    }
}
