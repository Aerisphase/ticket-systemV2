package main.java.com.ticketserver.service;

import com.ticketserver.dto.EventDto;
import com.ticketserver.model.Event;
import com.ticketserver.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EventDto getEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return convertToDto(event);
    }

    public EventDto saveEvent(EventDto eventDto) {
        Event event = convertToEntity(eventDto);
        Event savedEvent = eventRepository.save(event);
        return convertToDto(savedEvent);
    }

    public EventDto updateEvent(Long id, EventDto eventDto) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        modelMapper.map(eventDto, existingEvent);
        existingEvent.setId(id);
        Event updatedEvent = eventRepository.save(existingEvent);
        return convertToDto(updatedEvent);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private EventDto convertToDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

    private Event convertToEntity(EventDto eventDto) {
        return modelMapper.map(eventDto, Event.class);
    }
}