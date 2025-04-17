package main.java.com.ticketserver.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String holderName;
    private String holderEmail;
    private boolean reserved;
    private boolean paid;
    
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}