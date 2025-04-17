package main.java.com.ticketserver.controller;

import com.ticketserver.dto.EventDto;
import com.ticketserver.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @PostMapping
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        return eventService.saveEvent(eventDto);
    }

    @PutMapping("/{id}")
    public EventDto updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        return eventService.updateEvent(id, eventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}