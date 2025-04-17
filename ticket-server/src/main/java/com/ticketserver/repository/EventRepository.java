package main.java.com.ticketserver.repository;

import com.ticketserver.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}