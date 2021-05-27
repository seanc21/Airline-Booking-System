package com.company;

public class Traveler {
    String name = "";
    String flight = "";
    String from = "";
    String to = "";
    String seat = "";
    String code = "";
    Integer ticketCost = 0;

    public Traveler (String Name, String Option, String From, String To, String Seat, String Code, Integer ticket) {
        name = Name;
        flight = Option;
        from = From;
        to = To;
        seat = Seat;
        code = Code;
        ticketCost = ticket;
    }
}
