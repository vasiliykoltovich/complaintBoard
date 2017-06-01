package services.implementation;

import epam.beans.User;
import epam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.contract.GenericCRUDService;

import java.util.List;

@Service
public class UserServiceImpl implements GenericCRUDService<User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(User object) {
        return userRepository.findByLogin(object.getLogin());
    }


    @Override
    public User get(String parameter) {
        return userRepository.findByLogin(parameter);
    }

    @Override
    public User get(Long id) {
        return userRepository.findOne(id);
    }

    @Override

    public void create(User object) {
        userRepository.saveAndFlush(object);
    }

    @Override

    public void update(User object) {
        userRepository.saveAndFlush(object);
    }


    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    /**
     * @param id Long
     */
    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    /**
     * @return List
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean exists(User object) {
        return userRepository.existsByLogin(object.getLogin());

    }

    @Override
    public List<User> getAsListByAuthor(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAsListByOrganization(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getLatest5() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String parameter) {
        userRepository.deleteByLogin(parameter);

    }

}
