package wicket.resources;


import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import wicket.core.dao.WicketDaoWrapper;
import wicket.core.entity.User;
import wicket.db.jdbi.queries.UserQueries;
import wicket.db.jdbi.update.UserUpdate;
import wicket.db.jdbi.update.UserlogUpdate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@Ignore
public class UserResourceTest {

    private static final WicketDaoWrapper daoWrapper = mock(WicketDaoWrapper.class);

    private static final UserQueries queries = mock(UserQueries.class);
    private static final UserUpdate update = mock(UserUpdate.class);
    private static final UserlogUpdate logupdate = mock(UserlogUpdate.class);

    @Rule
    public final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new UserResource(queries, update, logupdate))
            .build();

    private final User user = new User(14L, "fourteensurname");

    @Before
    public void setup() {
        when(daoWrapper.getUserQueries().findByUserid(eq(14L))).thenReturn(user);
    }

    @Test
    public void testGetUser() throws Exception {
        assertThat(resources.client().target("rest/v0/wicket/user/14").request().get(User.class)).isEqualTo(user);
        verify(daoWrapper.getUserQueries().findByUserid(14L));
    }

    @After
    public void tearDown() throws Exception {
         reset(queries);
         reset(update);
         reset(logupdate);
    }

}