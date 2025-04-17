package main.java.com.ticketclient.gui;

import com.ticketclient.api.ApiClient;
import com.ticketclient.models.Event;
import com.ticketclient.models.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class MainFrame extends JFrame {
    private JList<Event> eventList;
    private DefaultListModel<Event> eventListModel;
    private JTextArea eventDetails;
    private JButton reserveButton;
    private JButton payButton;
    private JButton refreshButton;
    
    public MainFrame() {
        setTitle("Ticket Reservation System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Initialize components
        initComponents();
        
        // Load events
        loadEvents();
    }
    
    private void initComponents() {
        // Event list panel
        JPanel listPanel = new JPanel(new BorderLayout());
        eventListModel = new DefaultListModel<>();
        eventList = new JList<>(eventListModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showEventDetails(eventList.getSelectedValue());
            }
        });
        
        JScrollPane listScrollPane = new JScrollPane(eventList);
        listPanel.add(new JLabel("Available Events:"), BorderLayout.NORTH);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        
        // Event details panel
        JPanel detailsPanel = new JPanel(new BorderLayout());
        eventDetails = new JTextArea();
        eventDetails.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(eventDetails);
        detailsPanel.add(new JLabel("Event Details:"), BorderLayout.NORTH);
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this::refreshEvents);
        
        reserveButton = new JButton("Reserve Ticket");
        reserveButton.addActionListener(this::reserveTicket);
        reserveButton.setEnabled(false);
        
        payButton = new JButton("Pay for Ticket");
        payButton.addActionListener(this::payTicket);
        payButton.setEnabled(false);
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(reserveButton);
        buttonPanel.add(payButton);
        
        // Add components to frame
        add(listPanel, BorderLayout.WEST);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadEvents() {
        try {
            List<Event> events = ApiClient.getAllEvents();
            eventListModel.clear();
            events.forEach(eventListModel::addElement);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading events: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showEventDetails(Event event) {
        if (event == null) {
            eventDetails.setText("");
            reserveButton.setEnabled(false);
            return;
        }
        
        String details = String.format(
                "Name: %s\n\nDescription: %s\n\nDate: %s\n\nVenue: %s\n\nTotal Seats: %d\nAvailable Seats: %d",
                event.getName(), event.getDescription(), event.getDateTime(), 
                event.getVenue(), event.getTotalSeats(), event.getAvailableSeats());
        
        eventDetails.setText(details);
        reserveButton.setEnabled(event.getAvailableSeats() > 0);
    }
    
    private void refreshEvents(ActionEvent e) {
        loadEvents();
    }
    
    private void reserveTicket(ActionEvent e) {
        Event selectedEvent = eventList.getSelectedValue();
        if (selectedEvent == null) return;
        
        String name = JOptionPane.showInputDialog(this, "Enter your name:");
        if (name == null || name.trim().isEmpty()) return;
        
        String email = JOptionPane.showInputDialog(this, "Enter your email:");
        if (email == null || email.trim().isEmpty()) return;
        
        Ticket ticket = new Ticket();
        ticket.setEventId(selectedEvent.getId());
        ticket.setHolderName(name);
        ticket.setHolderEmail(email);
        
        try {
            Ticket reservedTicket = ApiClient.reserveTicket(ticket);
            JOptionPane.showMessageDialog(this, 
                    "Ticket reserved successfully!\nTicket ID: " + reservedTicket.getId(), 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            loadEvents();
            showEventDetails(selectedEvent);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                    "Error reserving ticket: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void payTicket(ActionEvent e) {
        // Implementation for paying for a ticket
    }
}