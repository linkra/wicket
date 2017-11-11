package wicket.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.netbeans.lib.cvsclient.commandLine.command.update;
import wicket.WicketApplication;
import wicket.WicketConfiguration;
import wicket.api.Saying;
import wicket.core.entity.User;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
            ConfigOverride.config("database.url", "jdbc:mysql://localhost:3306/wicket")); //jdbc:mysql://localhost:3306/wicket

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
        final User user = new User(11L, "Int", "Test", "8899", "inttest");
        final User newUser = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/wicket/user")
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(User.class);
        assertThat(newUser.getUserid()).isNotNull();
        assertThat(newUser.getFirstname()).isEqualTo(user.getFirstname());
    }



   /* @Test
    public void addUserSuccess() throws JsonProcessingException {
        User user2 = new User();
        when(update.insert(any(User.class))).thenReturn(user2);
        final Response response = RULE.target("/wicket/user")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(user2, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(update).insert(userCaptor.capture());
        //assertThat(userCaptor.getValue()).isEqualTo(user2); // FIXME! blir två olika objekt


    }           */

}