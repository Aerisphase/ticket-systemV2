package com.ticketclient.models;

public class Ticket {
    private Long id;
    private String holderName;
    private String holderEmail;
    private boolean reserved;
    private boolean paid;
    private Long eventId;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }
    public String getHolderEmail() { return holderEmail; }
    public void setHolderEmail(String holderEmail) { this.holderEmail = holderEmail; }
    public boolean isReserved() { return reserved; }
    public void setReserved(boolean reserved) { this.reserved = reserved; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
}