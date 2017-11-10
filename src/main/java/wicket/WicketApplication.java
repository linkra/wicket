package wicket;

import io.dropwizard.Application;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import wicket.db.jdbi.UserQueries;

import wicket.health.DatabaseHealthCheck;
import wicket.health.TemplateHealthCheck;
import wicket.resources.UserResource;
import wicket.resources.WicketResource;

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


    }

    @Override
    public void run(final WicketConfiguration config, final Environment environment) {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "mysql");
        
        final UserQueries dao = jdbi.onDemand(UserQueries.class);

        environment.jersey().register(new UserResource(dao));

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
    }





}
