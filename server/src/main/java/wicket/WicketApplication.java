package wicket;

import com.github.mustachejava.MustacheNotFoundException;
import com.google.common.base.Throwables;
import io.dropwizard.Application;


import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.spi.ExtendedExceptionMapper;
import org.skife.jdbi.v2.DBI;
import wicket.db.jdbi.queries.UserQueries;
import wicket.db.jdbi.queries.UserinfoQueries;
import wicket.db.jdbi.queries.UserlogQueries;
import wicket.db.jdbi.update.UserUpdate;
import wicket.db.jdbi.update.UserlogUpdate;
import wicket.health.DatabaseHealthCheck;
import wicket.health.TemplateHealthCheck;
import wicket.resources.UserResource;
import wicket.resources.UserinfoResource;
import wicket.resources.UserlogResource;
import wicket.resources.WicketResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.EnumSet;
import java.util.Map;

public class WicketApplication extends Application<WicketConfiguration> {

    public static void main(final String[] args) throws Exception {
        new WicketApplication().run(args);
    }

    @Override
    public String getName() {
        return "Wicket";
    }

    @Override
    public void initialize(final Bootstrap<WicketConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new ViewBundle<WicketConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(WicketConfiguration config) {
                return config.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(final WicketConfiguration config, final Environment environment) {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "mysql");
        
        final UserQueries userQueries = jdbi.onDemand(UserQueries.class);
        final UserUpdate userUpdate = jdbi.onDemand(UserUpdate.class);

        final UserlogQueries userlogQueries = jdbi.onDemand(UserlogQueries.class);
        final UserlogUpdate userlogUpdate = jdbi.onDemand(UserlogUpdate.class);
        final UserinfoQueries userinfoQueries = jdbi.onDemand(UserinfoQueries.class);

        environment.jersey().register(new UserResource(userQueries, userUpdate));
        environment.jersey().register(new UserlogResource(userlogQueries, userlogUpdate));
        environment.jersey().register(new UserinfoResource(userinfoQueries));


        final DatabaseHealthCheck dbihealthCheck =
                new DatabaseHealthCheck(jdbi, config.getDataSourceFactory().getValidationQuery() );

        environment.healthChecks().register("database", dbihealthCheck);


        // TEST
        final WicketResource resource = new WicketResource(
                config.getTemplate(),
                config.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(config.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);


        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(new ExtendedExceptionMapper<WebApplicationException>() {
            @Override
            public Response toResponse(WebApplicationException exception) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            @Override
            public boolean isMappable(WebApplicationException e) {
                return Throwables.getRootCause(e).getClass() == MustacheNotFoundException.class;
            }
        });

      /*  environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<Authentication.User>()
                .setAuthenticator(new ExampleAuthenticator())
                .setAuthorizer(new ExampleAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        */
    }





}
