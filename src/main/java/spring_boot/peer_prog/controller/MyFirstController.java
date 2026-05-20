package spring_boot.peer_prog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot.peer_prog.dto.NameRequest;
import spring_boot.peer_prog.dto.NameResponse;
import spring_boot.peer_prog.service.MyFirstService;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

/**
 * REST Controller for managing names.
 * Provides endpoints to retrieve all names and add new names.
 */

@RequestMapping("/app/v1")
@RestController
public class MyFirstController {

    private final static Logger log = LoggerFactory.getLogger(MyFirstController.class);

    private final MyFirstService myFirstService;

    /**
     * Constructor for dependency injection.
     *
     * @param myFirstService the service layer
     */
    public MyFirstController(MyFirstService myFirstService) {
        this.myFirstService = myFirstService;
    }

    /**
     * Retrieves all stored names.
     *
     * @return ResponseEntity containing a map of UUID to name
     */
    @GetMapping(value = "/get-name")
    public ResponseEntity<NameResponse> getNames() {
        log.info("Fetching all names");
        Map<UUID, String> names = myFirstService.getNames();
        NameResponse response = new NameResponse(names, "Names retrieved successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * Adds a new name to the system.
     *
     * @param request the request containing the name to add
     * @return ResponseEntity with HTTP 201 Created status and updated names map
     */
    @PostMapping(value = "/add-name")
    public ResponseEntity<String> addNames(@RequestBody NameRequest request) {
        if (request == null || request.name() == null || request.name().isBlank()) {
            log.warn("Invalid name request: name is null or empty");
            return ResponseEntity.badRequest().body(
                    "Name cannot be null or empty"
            );
        }

        log.info("Adding new name: {}", request.name());
        Map<UUID, String> response = myFirstService.saveData(request.name());

        return ResponseEntity
                .created(URI.create("/app/v1/add-name"))
                .body("Name added successfully");
    }

}
