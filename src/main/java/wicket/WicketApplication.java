package wicket;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: application initialization
    }

    @Override
    public void run(final WicketConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
