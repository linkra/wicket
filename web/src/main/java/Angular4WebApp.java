import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Angular4WebApp {

  public static void main(String[] args) throws Exception {
    // The simple Jetty config here will serve static content from the webapp directory
    String webappDirLocation = "/web/src/";

    // The port that we should run on can be set into an environment variable
    // Look for that variable and default to 8080 if it isn't there.
    String webPort = System.getenv("PORT");
    if (webPort == null || webPort.isEmpty()) {
      webPort = "8080";
    }
    Server server = new Server(Integer.valueOf(webPort));

    WebAppContext webapp = new WebAppContext();
    webapp.setContextPath("/");
    webapp.setDescriptor(webappDirLocation + "main/webapp/WEB-INF/web.xml");
    webapp.setResourceBase(webappDirLocation);

    server.setHandler(webapp);
    server.start();
    server.join();
  }
}
