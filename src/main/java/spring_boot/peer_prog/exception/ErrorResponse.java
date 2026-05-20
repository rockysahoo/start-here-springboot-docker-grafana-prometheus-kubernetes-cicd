package spring_boot.peer_prog.exception;


/**
 * Error response DTO for API errors.
 */
public record ErrorResponse(int status, String message, String details)
{ }

