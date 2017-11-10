package wicket.cli;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

public class WicketCommand extends Command{

    public WicketCommand() {
        super("hi", "Prints a greeting");
    }

    @Override
    public void configure(Subparser subparser) {
        subparser.addArgument("-u", "--user")
                .dest("user")
                .type(String.class)
                .required(true)
                .help("The user of the program");
    }

    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        System.out.println("Hi " + namespace.getString("user"));
    }
}
