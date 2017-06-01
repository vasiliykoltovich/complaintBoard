package services.implementation;

import epam.beans.Feedback;
import epam.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.contract.GenericCRUDService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements GenericCRUDService<Feedback, Long> {

    @Autowired
    private FeedbackRepository feedbackRepository;


    @Override
    public Feedback get(Feedback object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Feedback get(String parameter) {
        return feedbackRepository.findByAuthor_Login(parameter).get(0);
    }

    @Override
    public Feedback get(Long id) {
        return feedbackRepository.findOne(id);
    }

    @Override
    public void create(Feedback object) {
        feedbackRepository.saveAndFlush(object);
    }

    @Override
    public void update(Feedback object) {
        feedbackRepository.saveAndFlush(object);
    }


    @Override
    public void delete(Feedback object) {
        feedbackRepository.delete(object);
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        feedbackRepository.delete(id);
    }


    /**
     * @return List
     */
    @Override
    public List<Feedback> getAll() {
        return feedbackRepository.findAll().stream().sorted((a, b) -> b.getDate().compareTo(a.getDate())).collect(Collectors.toList());
    }

    public List<Feedback> getLatest5() {
        return feedbackRepository.findAll().stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(Feedback object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Feedback> getAsListByAuthor(String parameter) {
        return feedbackRepository.findByAuthor_Login(parameter);
    }

    @Override
    public List<Feedback> getAsListByOrganization(String parameter) {
        return feedbackRepository.findByOrganization_Name(parameter);
    }

    @Override
    public void delete(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
