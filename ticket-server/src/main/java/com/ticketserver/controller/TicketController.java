package main.java.com.ticketserver.controller;

import com.ticketserver.dto.TicketDto;
import com.ticketserver.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/event/{eventId}")
    public List<TicketDto> getTicketsByEvent(@PathVariable Long eventId) {
        return ticketService.getTicketsByEvent(eventId);
    }

    @PostMapping
    public TicketDto reserveTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.reserveTicket(ticketDto);
    }

    @PutMapping("/{id}/pay")
    public TicketDto payTicket(@PathVariable Long id) {
        return ticketService.payTicket(id);
    }

    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long id) {
        ticketService.cancelReservation(id);
    }
}