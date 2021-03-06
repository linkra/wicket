package wicket.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import wicket.api.Saying;
import wicket.core.entity.User;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("rest/v0/wicket")
@Produces(MediaType.APPLICATION_JSON)
public class WicketResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public WicketResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    @GET
    @Path("/greeting")
    public String getGreeting(@Auth Optional<User> userOpt) {
        return userOpt.map(user -> "Hello, " + user.getName() + "!").orElse("Greetings, anonymous visitor!");
    }
}
