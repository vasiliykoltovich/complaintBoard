package services;

import epam.beans.Feedback;
import epam.beans.Organization;
import epam.beans.User;
import epam.repository.FeedbackRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import services.contract.GenericCRUDService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by djedi on 03.06.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={com.epam.bootapplication.BoardApplication.class})
@WebMvcTest(controllers = {com.epam.web.controllers.FeedbackController.class, com.epam.web.controllers.AbstractController.class})
public class FeedbackServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext ac;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private FeedbackRepository feedbackRepository;

    @MockBean
    private GenericCRUDService<Feedback, Long> feedbackService;

    @MockBean
    private GenericCRUDService<Organization, Long> organizationService;

    @MockBean
    private GenericCRUDService<User, Long> userService;


    private Feedback feedback;



    @Before
    public void init() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

//        mockMvc = MockMvcBuilders.standaloneSetup(new FeedbackController()).build();
        feedback = new Feedback(new Long(1), "text", new User(), 1, 1);
        List<Feedback> list = new ArrayList<>();
        list.add(feedback);

//        when(feedbackService.getAll()).thenReturn(list);
//        when(feedbackRepository.findAll()).thenReturn(list);

    }


//    @Test
//    public void testfindAll_whenCallToGetAllFeedbacks() throws Exception {
//        when(feedbackServiceImplMock.getAll()).thenReturn(Arrays.asList(feedback));
//
//
//        mockMvc.perform(get("/manageFeedbacks"))
//                .andExpect(status().isOk());
////                .andExpect(view().name("todo/list"))
////                .andExpect(forwardedUrl("/WEB-INF/jsp/todo/list.jsp"))
////                .andExpect(model().attribute("todos", hasSize(1)))
////                .andExpect(model().attribute("todos", hasItem(
////                        allOf(
////                                hasProperty("id", is(1L)),
////                                hasProperty("description", is("Lorem ipsum")),
////                                hasProperty("title", is("Foo"))
////                        )
////                )))
////                .andExpect(model().attribute("todos", hasItem(
////                        allOf(
////                                hasProperty("id", is(2L)),
////                                hasProperty("description", is("Lorem ipsum")),
////                                hasProperty("title", is("Bar"))
////                        )
////                )));
//
//        verify(feedbackServiceImplMock, times(1)).getAll();
//    }

    @Test
    public void test() throws Exception {
//        Whitebox.setInternalState(feedbackService, "feedbackRepository", feedbackRepositoryMock);
//        assertThat(feedbackService).isNotNull();
        mockMvc.perform(get("/manageFeedbacks"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andDo(print());

    }


}
