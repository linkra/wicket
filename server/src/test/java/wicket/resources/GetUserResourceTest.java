package wicket.resources;


import io.dropwizard.testing.junit.ResourceTestRule;

import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;
import wicket.core.entity.User;
import wicket.db.jdbi.queries.UserQueries;
import wicket.db.jdbi.update.UserUpdate;
import wicket.db.jdbi.update.UserlogUpdate;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class GetUserResourceTest {
    private static final UserQueries queries = mock(UserQueries.class);
    private static final UserUpdate update = mock(UserUpdate.class);
    private static final UserlogUpdate logupdate = mock(UserlogUpdate.class);

    @ClassRule
    public static final ResourceTestRule RULE = ResourceTestRule.builder()
            .addResource(new UserResource(queries, update, logupdate))
            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
            .build();

    @Captor
    private ArgumentCaptor<User> userCaptor;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUserid(10L);
        user.setAddress("my way");
        user.setEmail("info@wicket.se");
        user.setFirstname("Karin");
        user.setSurname("Ek");
        user.setPwd("001ggj");
        user.setUsername("ekan");
    }

    @After
    public void tearDown() throws Exception {
        reset(queries);
        reset(update);
    }

    @Test
    public void findByIdSuccess() throws Exception {
        when(queries.findByUserid(10L)).thenReturn(user);
        User found = RULE.target("/wicket/user/10").request().get(User.class);
        assertThat(found.getUserid()).isEqualTo(user.getUserid());
        verify(queries).findByUserid(10L);
    }

    @Test
    public void findByUsernameSuccess() throws Exception {
        when(queries.findByUserid(10L)).thenReturn(user);
        User found = RULE.target("/wicket/user/10").request().get(User.class);
        assertThat(found.getUserid()).isEqualTo(user.getUserid());
        verify(queries).findByUserid(10L);
    }


}