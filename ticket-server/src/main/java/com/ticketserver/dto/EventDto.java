package main.java.com.ticketserver.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private String venue;
    private int totalSeats;
    private int availableSeats;
}