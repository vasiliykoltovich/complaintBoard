package services;

import config.RepositoryConfig;
import epam.beans.Feedback;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.implementation.FeedbackServiceImpl;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringJUnit4ClassRunner.class)
public class FeedbackServiceMockitoTest extends RepositoryConfig {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @InjectMocks
    FeedbackServiceImpl feedbackLongGenericCRUDServiceMock;


    @Test
    public void testGetAllFeedbacks_throwException() {

        List<Feedback> feedbacks = feedbackLongGenericCRUDServiceMock.getAll();
        verify(feedbackRepositoryMock).findAll();
        assertNotNull(feedbacks);

    }


    @Test
    public void testGetFeedback_whenStringParameter_returnFeedback() {
        List<Feedback> feedbacks = feedbackLongGenericCRUDServiceMock.getAsListByAuthor(anyString());
        verify(feedbackRepositoryMock).findByAuthor_Login(anyString());
        assertNotNull(feedbacks);

    }

    @Test
    public void testGetFeedback_whenLongArgument_returnFeedback() {
        Feedback feedback = feedbackLongGenericCRUDServiceMock.get(anyLong());
        verify(feedbackRepositoryMock).findOne(anyLong());
        assertNotNull(feedback);

    }

    @Test
    public void testDeleteFeedbackbyId_throwException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Test exception");
        feedbackLongGenericCRUDServiceMock.delete(anyLong());
        verify(feedbackRepositoryMock, times(1)).delete(anyLong());

    }

    @Test
    public void testDeleteFeedbackByFeedback_throwException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Test exception");
        feedbackLongGenericCRUDServiceMock.delete(new Feedback());
        verify(feedbackRepositoryMock, times(1)).delete(any(Feedback.class));

    }

    @Test
    public void testCreateFeedback_whenFeedbackArgument_returnFeedback() {

        exception.expect(RuntimeException.class);
        exception.expectMessage("Test creation and update exception");
        feedbackLongGenericCRUDServiceMock.create(any(Feedback.class));
        verify(feedbackRepositoryMock).saveAndFlush(any(Feedback.class));

    }



}
