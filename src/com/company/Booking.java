package com.company;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;

public class Booking {
    DecimalFormat decimalFormat = new DecimalFormat("##0.00");
    ArrayList<Traveler> list = new ArrayList<>();
    ArrayList<Traveler> delete = new ArrayList<>();
    static ArrayList<String> seats = new ArrayList<String>();
    Scanner sc = new Scanner(System.in);
    Integer balance = 0;
    Integer ticket = 0;

    public void travelerInfo(int travelers, String flight, String From, String To, String code) {
        for (int i = 1; i < travelers + 1; i++) {
            System.out.println("Passenger " + i + " name: ");
            String name = sc.next();
            String seat = null;
            boolean found = false;
            while (!found) {
                System.out.println("Pick a seat!");
                seat = sc.next();
                if (seatAvailable(seat)) {
                    if (From.equalsIgnoreCase("BHM") && To.equalsIgnoreCase("BZE")
                            || From.equalsIgnoreCase("BZE") && To.equalsIgnoreCase("BHM")) {
                        addTicketAmount(351);
                    } else if (From.equalsIgnoreCase("MEM") && To.equalsIgnoreCase("JFK")
                            || From.equalsIgnoreCase("JFK") && To.equalsIgnoreCase("MEM")) {
                        addTicketAmount(116);
                    }

                    if (To.equalsIgnoreCase("LAX")) {
                        addTicketAmount(101);
                    } else if (To.equalsIgnoreCase("ATL")) {
                        addTicketAmount(50);
                    } else if (To.equalsIgnoreCase("MEX")) {
                        addTicketAmount(225);
                    } else if (To.equalsIgnoreCase("ORD")) {
                        addTicketAmount(89);
                    }
                    firstClassTicket(seat);
                    ticket = this.balance;
                    seats.add(seat);
                    Traveler traveler = new Traveler(name, flight, From, To, seat, code, ticket);
                    list.add(traveler);
                    found = true;
                    this.balance = 0;
                } else {
                    System.err.println(seat + " is Unavailable");
                }
            }
        }
    }

    // Money Transaction //
    public void addTicketAmount(Integer amount) {
        this.balance += amount;
    }

    public void tripBalance(String code) {
        Integer totalAmount = 0;
        for (Traveler passenger : list) {
            if (code.equalsIgnoreCase(passenger.code)) {
                totalAmount += passenger.ticketCost;
            }
        }
        String total = decimalFormat.format(totalAmount);
        System.out.println("The total will be $" + total + "\n");
    }

    public void tripRefund(String code) {
        Integer totalAmount = 0;
        for (Traveler passenger : list) {
            if (code.equalsIgnoreCase(passenger.code)) {
                totalAmount += passenger.ticketCost;
            }
        }
    }

    public void firstClassTicket(String seat) {
        if (seat.equalsIgnoreCase("1A") || seat.equalsIgnoreCase("1B") || seat.equalsIgnoreCase("2A") || seat.equalsIgnoreCase("2B")
            || seat.equalsIgnoreCase("3A") || seat.equalsIgnoreCase("3B") || seat.equalsIgnoreCase("4A") || seat.equalsIgnoreCase("4B")) {
            addTicketAmount(85);
            System.out.println(seat + " is a First Class seat!");
        } else {
            addTicketAmount(0);
            System.out.println(seat + " is a Main Cabin seat!");
        }
    }

    // Available seats or right flight validation //
    public static boolean seatAvailable(String seat) {
        for (String chair : seats) { // for ( int i =o; int i == seats[]; i ++ ) seats{i}
            if (seat.equals(chair)) {
                return false;
            }
        }
        return seat.matches("^[1-9].*$") && seat.endsWith("A") || seat.endsWith("B") || seat.endsWith("a") || seat.endsWith("b");
    }

    public void unavailableSeats() {
        System.out.println("Unavailable Seats");
        System.out.println(seats);
        if (seats.isEmpty()) {
            System.out.println("No one has reserved seats yet!\n");
        }
    }

    public boolean rightFlightRoundtrip(String from, String to) {
        if (from.equalsIgnoreCase("BHM") && to.equalsIgnoreCase("BZE")
                || from.equalsIgnoreCase("BZE") && to.equalsIgnoreCase("BHM")) {
//            addTicketAmount(351);
            return true;
        } else if (from.equalsIgnoreCase("MEM") && to.equalsIgnoreCase("JFK")
                || from.equalsIgnoreCase("JFK") && to.equalsIgnoreCase("MEM")) {
//            addTicketAmount(116);
            return true;
        } else {
            return false;
        }
    }

    public boolean rightFlightOneway(String to) {
        if (to.equalsIgnoreCase("LAX")) {
            return true;
        } else if (to.equalsIgnoreCase("ATL")) {
            return true;
        } else if (to.equalsIgnoreCase("MEX")) {
            return true;
        } else if (to.equalsIgnoreCase("ORD")) {
            return true;
        } else {
            return false;
        }
    }

    // Viewing passenger tickets //
    public void viewTicket(String name) {
        for (Traveler passenger : list) {
            if (name.equalsIgnoreCase(passenger.code)) {
                String ticketCost = decimalFormat.format(passenger.ticketCost);
                System.out.print("Passenger: " + passenger.name + ", Flight: " + passenger.flight +
                        ", From: " + passenger.from + ", To: " + passenger.to + ", Seat " + passenger.seat +
                        ", Code: " + passenger.code + ", Ticket Cost: $" + ticketCost + "\n");
            }
        }
    }

    public void viewTicketWCode(String code) {
        for (Traveler passenger : list) {
            if (code.equalsIgnoreCase(passenger.code)) {
                System.out.print("Passenger: " + passenger.name + ", Flight: " + passenger.flight +
                        ", From: " + passenger.from + ", To: " + passenger.to + ", Seat " + passenger.seat +
                        ", Code: " + passenger.code + "\n");
            }
        }
    }

    // Testing //
    public void viewTickets() {
        for (Traveler passenger : list) {
            System.out.println("_____Testing_____");
            System.out.print("Passenger: " + passenger.name + ", Flight: " + passenger.flight +
                    ", From: " + passenger.from + ", To: " + passenger.to + ", Seat " + passenger.seat +
                    ", Code: " + passenger.code + ", Ticket Cost: " + passenger.ticketCost + "\n");
        }
    }

    public static String confirmationNumber() {
        int length = 6;
        String code = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // 36 letter.
        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomCode.append(code.charAt(random.nextInt(code.length())));
        }
        return randomCode.toString();
    }

    // Check-In //
    public void checkIn(String code) {
        boolean found = false;
        for (Traveler passenger : list) {
            if (code.equalsIgnoreCase(passenger.code)) {
                String ticketCost = decimalFormat.format(passenger.ticketCost);
                System.out.print("Passenger: " + passenger.name + ", Flight: " + passenger.flight +
                        ", From: " + passenger.from + ", To: " + passenger.to + ", Seat " + passenger.seat +
                        " Ticket Cost: $" + ticketCost + "\n");
                found = true;
            }
        }
        if (!found) {
            System.err.println(code + " doesn't exist.");
        } else {
            tripBalance(code);
            System.out.println("Type 'Confirm'");
            String confirm = sc.next();
            if (confirm.equalsIgnoreCase("confirm")) {
                deleteConfirmationNumber(code);
                System.out.println("Check-in Successfully!");
            } else {
                System.err.println("BOT?!");
            }
        }
    }

    // Making sure the code is not reusable after check-in //
    public void deleteConfirmationNumber(String code) {
        boolean found = false;
        for (Traveler passenger : list) {
            if (code.equalsIgnoreCase(passenger.code)) {
                delete.add(passenger);
            }
            found = true;
        }
        if (!found) {
            System.out.println(code + " doesn't exist.");
        }
        if (found) {
            for (Traveler removePassenger : delete) {
                list.remove(removePassenger);
            }
            delete.clear();
        }
    }

    // Updating flight //
    public void update(String inputName, String change, String changed) {
        boolean found = false;
        for (Traveler passenger : list) {
            if (inputName.equalsIgnoreCase(passenger.name)) {
                if (change.equalsIgnoreCase("name")) {
                    passenger.name = changed;
                    found = true;
                    System.out.println("Updated!");
                } else if (change.equalsIgnoreCase("flight")) {
                    if (passenger.flight.equalsIgnoreCase("roundtrip")) {
                        passenger.flight = changed;
                        System.out.println("changing to oneway!");
                        System.out.println("""
                                These are the available flights from Memphis
                                * Oneway Trip --> Birmingham BHM $50
                                * Oneway Trip --> Belize City BZE $265
                                * Oneway Trip --> Chicago O'Hare ORD $89
                                * Oneway Trip --> New York JFK $101""");
                        while (true) {
                            System.out.println("Where are you traveling to?");
                            String to = sc.next();
                            if (rightFlightOneway(to)) {
                                passenger.to = to;
                                passenger.from = "MEM";
                                System.out.println("Flight process");
                                break;
                            } else {
                                System.out.println("Pick a Destination");
                            }
                        }
                        found = true;
                        System.out.println("Updated!");
                    } else if (passenger.flight.equalsIgnoreCase("oneway")) {
                        passenger.flight = changed;
                        System.out.println("changing to roundtrip!");
                        System.out.println("""
                        These are the available flights
                        * Birmingham BHM <- 6h 23m -> Belize City BZE $351
                        * Memphis MEM <- 2h 57m -> New York JFK $116""");
                        while (true) {
                            System.out.println("Where are you traveling from?");
                            String from = sc.next();
                            System.out.println("where are you traveling to?");
                            String to = sc.next();
                            if (rightFlightRoundtrip(from, to)) {
                                System.out.println("Flight process");
                                passenger.from = from;
                                passenger.to = to;
                                break;
                            } else {
                                System.out.println("Pick your departure and destination?");
                            }
                        }
                        found = true;
                        System.out.println("Updated!");
                    } else {
                        System.out.println("flights are only Roundtrip and Oneway");
                    }
                } else if (change.equalsIgnoreCase("seat")) {
                    if (seatAvailable(changed)) {
                        System.out.println(changed + " Picked!");
                        seats.remove(passenger.seat);
                        seats.add(changed);
                        passenger.seat = changed;
                        found = true;
                        System.out.println("Updated!");
                    } else {
                        System.out.println(changed + " is Unavailable");
                    }
                }
            }
        }
        if (!found) {
            System.err.println(inputName + " doesn't exist.");
        }
    }

    // Verifying confirmation code.
    public boolean correctCode(String code) {
        for (Traveler passenger : list) {
            if (code.equals(passenger.code)) {
                return true;
            }
        }
        return false;
    }

    // Refunding passenger ticket //
    public void refund(String code) {
        boolean found = false;
        Integer totalAmount = 0;
        for (Traveler passenger : list) {
            if (code.equalsIgnoreCase(passenger.code)) {
                totalAmount += passenger.ticketCost;
                delete.add(passenger);
                found = true;

            }
        }
        if (!found) {
            System.err.println(code + " doesn't exist.");
        }
        if (found) {
            String total = decimalFormat.format(totalAmount);
            System.out.println("Refund was successful of $" + total +" and will be in your bank account within 2-3 business days.");
            System.out.println("Thanks for choosing Beyond's Airline\n");
            for (Traveler removePassenger : delete) {
                list.remove(removePassenger);
                seats.remove(removePassenger.seat);
            }
            delete.clear();
        }
    }
}