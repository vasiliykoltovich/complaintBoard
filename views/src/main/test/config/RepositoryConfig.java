package config;

import epam.beans.Feedback;
import epam.beans.Organization;
import epam.beans.User;
import epam.repository.FeedbackRepository;
import epam.repository.OrganizationRepository;
import epam.repository.UserRepository;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


public class RepositoryConfig {


    @Mock
    protected FeedbackRepository feedbackRepositoryMock;
    @Mock
    protected UserRepository userRepositoryMock;
    @Mock
    protected OrganizationRepository organizationRepositoryMock;
    private User user = new User(1L, "test", "test");
    private Organization organization = new Organization(1L, "test");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //init variables
        Feedback feedback = new Feedback(new Long(1), "text", new User(), 1, 1);
        List<Feedback> list = new ArrayList<>();
        list.add(feedback);


        List<User> users = new ArrayList<>();
        users.add(user);


        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        when(feedbackRepositoryMock.findAll()).thenReturn(list);
        when(feedbackRepositoryMock.findByAuthor_Login(anyString())).thenReturn(list);
        when(feedbackRepositoryMock.findByOrganization_Name(anyString())).thenReturn(list);
        when(feedbackRepositoryMock.deleteByAuthor_Login(anyString())).thenReturn(1L);
        when(feedbackRepositoryMock.deleteById(anyLong())).thenReturn(1L);
        when(feedbackRepositoryMock.findOne(anyLong())).thenReturn(feedback);


        doThrow(new RuntimeException("Test exception")).when(feedbackRepositoryMock).delete(any(Feedback.class));
        doThrow(new RuntimeException("Test exception")).when(feedbackRepositoryMock).delete(any(Long.class));
        doThrow(new RuntimeException("Test creation and update exception")).when(feedbackRepositoryMock).saveAndFlush(any(Feedback.class));


        when(userRepositoryMock.findAll()).thenReturn(users);
        when(userRepositoryMock.findByLogin(anyString())).thenReturn(user);
        when(userRepositoryMock.findOne(anyLong())).thenReturn(user);
        when(userRepositoryMock.existsByLogin(anyString())).thenReturn(true);
        when(userRepositoryMock.exists(anyLong())).thenReturn(true);
        when(userRepositoryMock.deleteByLogin(anyString())).thenReturn(1L);
        doThrow(new RuntimeException("Test user delete by id exception")).when(userRepositoryMock).delete(any(Long.class));
        doThrow(new RuntimeException("Test user delete by User object exception")).when(userRepositoryMock).delete(any(User.class));
        doThrow(new RuntimeException("Test creation and update exception")).when(userRepositoryMock).saveAndFlush(any(User.class));

        when(userRepositoryMock.deleteByLogin(anyString())).thenReturn(1L);


        when(organizationRepositoryMock.findAll()).thenReturn(organizations);
        when(organizationRepositoryMock.findByName(anyString())).thenReturn(organization);
        when(organizationRepositoryMock.findOne(anyLong())).thenReturn(organization);
        when(organizationRepositoryMock.exists(anyLong())).thenReturn(true);
        when(organizationRepositoryMock.deleteByName(anyString())).thenReturn(1L);
        doThrow(new RuntimeException("Test organization delete by id exception")).when(organizationRepositoryMock).delete(any(Long.class));
        doThrow(new RuntimeException("Test organization delete by Organization object exception")).when(organizationRepositoryMock).delete(any(Organization.class));
        doThrow(new RuntimeException("Test create and update exception")).when(organizationRepositoryMock).save(any(Organization.class));


    }


}
