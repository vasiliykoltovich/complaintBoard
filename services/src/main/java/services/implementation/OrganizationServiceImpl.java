package services.implementation;

import epam.beans.Organization;
import epam.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.contract.GenericCRUDService;

import java.util.List;

@Service
public class OrganizationServiceImpl implements GenericCRUDService<Organization, Long> {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization get(Organization object) {
        return organizationRepository.findByName(object.getName());
    }

    @Override
    public Organization get(String parameter) {
        return organizationRepository.findByName(parameter);
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public Organization get(Long id) {
        return organizationRepository.findOne(id);
    }

    @Override
    public void create(Organization object) {
        organizationRepository.save(object);
    }

    @Override
    public void update(Organization object) {
        organizationRepository.save(object);
    }


    @Override
    public void delete(Organization object) {
        organizationRepository.delete(object);
    }

    /**
     * @param id Long
     */
    @Override
    public void delete(Long id) {
        organizationRepository.delete(id);
    }

    @Override
    public void delete(String parameter) {
        organizationRepository.deleteByName(parameter);
    }


    /**
     * @return List
     */
    @Override
    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }

    public List<String> getAllAsNameList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(Organization object) {
        return organizationRepository.exists(object.getId());
    }

    @Override
    public List<Organization> getAsListByAuthor(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getAsListByOrganization(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> getLatest5() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
