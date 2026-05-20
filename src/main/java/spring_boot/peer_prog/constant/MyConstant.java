package spring_boot.peer_prog.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Data store component for managing names.
 * Stores a mapping of UUID to string names.
 * Thread-safe implementation using ConcurrentHashMap.
 */
@Component
public class MyConstant {

    private static final Logger log = LoggerFactory.getLogger(MyConstant.class);
    private final Map<UUID, String> map = new ConcurrentHashMap<>();

    /**
     * Adds a new name to the data store.
     *
     * @param name the name to add (whitespace will be trimmed)
     * @return the updated map of all names
     */
    public Map<UUID, String> addData(String name) {
        UUID id = UUID.randomUUID();
        String cleanedName = name.replaceAll("\\r\\n ", "").trim();
        map.put(id, cleanedName);
        log.info("Data added with ID: {} and value: {}", id, cleanedName);
        return new HashMap<>(map);
    }

    /**
     * Retrieves all stored names.
     *
     * @return a copy of the current map of names
     */
    public Map<UUID, String> getData() {
        log.debug("Retrieving all data from store. Total entries: {}", map.size());
        return new HashMap<>(map);
    }
}

