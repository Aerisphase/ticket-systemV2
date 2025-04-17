package main.java.com.ticketserver.dto;

import lombok.Data;

@Data
public class TicketDto {
    private Long id;
    private String holderName;
    private String holderEmail;
    private boolean reserved;
    private boolean paid;
    private Long eventId;
}
