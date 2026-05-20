package spring_boot.peer_prog.dto;

import java.util.Map;
import java.util.UUID;

/**
 * DTO for API responses containing names.
 */
public record NameResponse(Map<UUID, String> data, String message)
{ }

