package services;

import config.RepositoryConfig;
import epam.beans.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.implementation.UserServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceMockitoTest extends RepositoryConfig {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @InjectMocks
    UserServiceImpl userServiceMock;


    @Test
    public void testGetAllUsers_returnList() {

        List<User> users = userServiceMock.getAll();
        verify(userRepositoryMock).findAll();
        assertNotNull(users);

    }


    @Test
    public void testGetUser_whenStringParameter_returnUser() {
        User user = userServiceMock.get(anyString());
        verify(userRepositoryMock).findByLogin(anyString());
        assertNotNull(user);

    }

    @Test
    public void testGetUser_whenLongArgument_returnUser() {
        User user = userServiceMock.get(anyLong());
        verify(userRepositoryMock).findOne(anyLong());
        assertNotNull(user);
        assertEquals(user.getLogin(), "test");
        assertEquals(user.getPassword(), "test");

    }

    @Test
    public void testDeleteUserById_throwException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Test user delete by id exception");
        userServiceMock.delete(anyLong());
        verify(userRepositoryMock, times(1)).delete(anyLong());

    }

    @Test
    public void testDeleteUserByObject_throwException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Test user delete by User object exception");
        userServiceMock.delete(new User());
        verify(userRepositoryMock, times(1)).delete(any(User.class));

    }

    @Test
    public void testCreateUser_whenUserArgument_throwException() {

        exception.expect(RuntimeException.class);
        exception.expectMessage("Test creation and update exception");
        userServiceMock.create(any(User.class));
        verify(userRepositoryMock).saveAndFlush(any(User.class));


    }


}
