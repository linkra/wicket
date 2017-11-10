package wicket;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import wicket.health.TemplateHealthCheck;
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
        // TODO: github test
    }

    @Override
    public void run(final WicketConfiguration configuration, final Environment environment) {

        final WicketResource resource = new WicketResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);
    }

}
