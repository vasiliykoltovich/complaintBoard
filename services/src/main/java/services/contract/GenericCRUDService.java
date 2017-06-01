package services.contract;

import java.io.Serializable;
import java.util.List;

public interface GenericCRUDService<ResponseType, ID extends Serializable> {

    /**
     * @param object
     * @return
     */
    ResponseType get(ResponseType object);

    /**
     * @param parameter
     * @return
     */
    ResponseType get(String parameter);

    /**
     * @param id
     * @return
     */
    ResponseType get(ID id);

    /**
     * @param object
     */
    void create(ResponseType object);

    /**
     * @param object
     */
    void update(ResponseType object);

    /**
     * @param object
     */
    void delete(ResponseType object);

    /**
     * 
     * @param parameter 
     */
    void delete(String parameter);

    /**
     * @param id
     * @param object
     */
    void delete(ID id);

    /**
     * @param object
     * @return boolean
     */
    boolean exists(ResponseType object);

    /**
     * @return List
     */
    List<ResponseType> getAll();

    /**
     * @param parameter
     * @return
     */
    public List<ResponseType> getAsListByAuthor(String parameter);

    /**
     * @param parameter
     * @return
     */
    public List<ResponseType> getAsListByOrganization(String parameter);

    /**
     * @return
     */
    List<ResponseType> getLatest5();
}
