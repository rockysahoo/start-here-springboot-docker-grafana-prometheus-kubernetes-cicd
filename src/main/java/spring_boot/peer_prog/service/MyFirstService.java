package spring_boot.peer_prog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring_boot.peer_prog.constant.MyConstant;

import java.util.Map;
import java.util.UUID;

/**
 * Service class for managing name operations.
 * Acts as a bridge between the controller and data storage.
 */

@Service
public class MyFirstService {

    private final static Logger log= LoggerFactory.getLogger(MyFirstService.class);

    private final MyConstant myConstant;

    /**
     * Constructor for dependency injection.
     *
     * @param myConstant the constant/data store component
     */
    public MyFirstService(MyConstant myConstant) {
        this.myConstant = myConstant;
    }

    /**
     * Retrieves all stored names.
     *
     * @return a map of UUID to name
     */
    public Map<UUID, String> getNames() {
        log.debug("Retrieving all names from data store");
        return myConstant.getData();
    }

    /**
     * Saves a new name to the data store.
     *
     * @param name the name to save
     * @return the updated map of names
     */
    public Map<UUID, String> saveData(String name) {
        log.debug("Saving name to data store: {}", name);
        return myConstant.addData(name);

    }
}
