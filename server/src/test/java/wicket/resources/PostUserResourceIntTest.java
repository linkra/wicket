package wicket.resources;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import wicket.WicketApplication;
import wicket.WicketConfiguration;
import wicket.core.entity.User;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
public class PostUserResourceIntTest {

    private static final String TMP_FILE = createTempFile();
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test.yml");

    @ClassRule
    public static final DropwizardAppRule<WicketConfiguration> RULE = new DropwizardAppRule<>(
            WicketApplication.class, CONFIG_PATH,
            ConfigOverride.config("database.url", "jdbc:mysql://localhost:3306/wicket"));

    @BeforeClass
    public static void migrateDb() throws Exception {
        RULE.getApplication().run("database", "migrate", CONFIG_PATH);
    }

    private static String createTempFile() {
        try {
            return File.createTempFile("test-wicket", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
   
    @Test
    public void testPostUser() throws Exception {
        final User user = new User(11L, "Ett", "Test", "8899", "inttest");
        final User newUser = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/rest/v0/wicket/user/register")
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(User.class);
        assertThat(newUser.getUserid()).isNotNull();
        assertThat(newUser.getFirstname()).isEqualTo(user.getFirstname());
    }


}