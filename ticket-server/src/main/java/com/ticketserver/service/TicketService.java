package main.java.com.ticketserver.service;

import com.ticketserver.dto.TicketDto;
import com.ticketserver.model.Ticket;
import com.ticketserver.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    public List<TicketDto> getTicketsByEvent(Long eventId) {
        return ticketRepository.findByEventId(eventId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TicketDto reserveTicket(TicketDto ticketDto) {
        Ticket ticket = convertToEntity(ticketDto);
        ticket.setReserved(true);
        ticket.setPaid(false);
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDto(savedTicket);
    }

    public TicketDto payTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setPaid(true);
        Ticket paidTicket = ticketRepository.save(ticket);
        return convertToDto(paidTicket);
    }

    public void cancelReservation(Long id) {
        ticketRepository.deleteById(id);
    }

    private TicketDto convertToDto(Ticket ticket) {
        return modelMapper.map(ticket, TicketDto.class);
    }

    private Ticket convertToEntity(TicketDto ticketDto) {
        return modelMapper.map(ticketDto, Ticket.class);
    }
}