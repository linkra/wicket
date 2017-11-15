package wicket.core.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private static final ObjectMapper MAPPERWRITE = Jackson.newObjectMapper();
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void serializesSmallUserToJSON() throws Exception {
        final User user = new User(10L, "Ek");

        final String expected = MAPPERWRITE.writeValueAsString(
                MAPPERWRITE.readValue(fixture("fixtures/usersmall"), User.class));

        assertThat(MAPPERWRITE.writeValueAsString(user)).isEqualTo(expected);
    }

    @Test
    public void serializesFullUserToJSON() throws Exception {
        final User user = new User(11L, "Karl","Ekskog", "Ekvägen 25, 889 00 Östersund, Sweden",
                "kalle@ostersund.se", "jjjhhh", "kalle");

        final String expected = MAPPERWRITE.writeValueAsString(
                MAPPERWRITE.readValue(fixture("fixtures/userfull"), User.class));

        assertThat(MAPPERWRITE.writeValueAsString(user)).isEqualTo(expected);
    }

    @Test
    public void deserializesSmallUserFromJSON() throws Exception {
        final User user = new User(12L, "Ekström");
        User user1 = MAPPER.readValue(fixture("fixtures/usersmall"), User.class);
        assertThat(user.getUserid().equals(user1.getUserid()));
        assertThat(user.getSurname().equals(user1.getSurname()));
    }



}