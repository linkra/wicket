package wicket.db.bundles;

import io.dropwizard.Bundle;
import io.dropwizard.jdbi.jersey.LoggingDBIExceptionMapper;
import io.dropwizard.jdbi.jersey.LoggingSQLExceptionMapper;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DBIExceptionsBundle implements Bundle {
    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        
    }

    @Override
    public void run(Environment environment) {
        //environment.jersey().register(new LoggingSQLExceptionMapper());
       // environment.jersey().register(new LoggingDBIExceptionMapper());
    }
}
