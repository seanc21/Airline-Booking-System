package com.company;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Booking {
    ArrayList<Traveler> list = new ArrayList<>();
    ArrayList<Traveler> delete = new ArrayList<>();
    static ArrayList<String> seats = new ArrayList<String>();
    Scanner sc = new Scanner(System.in);
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
                    System.out.println(seat + " Picked!");
                    seats.add(seat);
                    found = true;
                } else {
                    System.out.println(seat + " is Unavailable");
                }
            }
            Traveler traveler = new Traveler(name, flight, From, To, seat, code);
            list.add(traveler);
        }
    }

    public static boolean seatAvailable(String seat) {
        for (String chair : seats) { // for ( int i =o; int i == seats[]; i ++ ) seats{i}
            if (seat.equals(chair)) {
                return false;
            }
        }
        return seat.matches("^[1-9].*$") && seat.endsWith("A") || seat.endsWith("B") || seat.endsWith("a") || seat.endsWith("b");
    }

    public void unavailableSeats(){
        System.out.println("Unavailable Seats");
        System.out.println(seats);
        if (seats.isEmpty()) {
            System.out.println("No one has reserved seats yet!\n");
        }
    }

    public void rightFlight(String from, String to) {
        if (correctFlight(from, to)) {
            System.out.println("Flight Processing");
        } else {
            System.out.println("Flight not found");
        }
    }

    public static boolean correctFlight(String from, String to) {
        return from.equalsIgnoreCase("BHM") && to.equalsIgnoreCase("BZE")
                || from.equalsIgnoreCase("BZE") && to.equalsIgnoreCase("BHM");
    }

    public void viewTicket(String name) {
        for (Traveler passenger : list) {
            if (name.equalsIgnoreCase(passenger.code)) {
                System.out.print("Passenger: " + passenger.name + ", Flight: "+ passenger.flight +
                        ", From: " + passenger.from + ", To: " + passenger.to + ", Seat " + passenger.seat +
                        ", Code: " + passenger.code + "\n");
            }
        }
    }

    public void viewTickets() {
        for (Traveler passenger : list) {
            System.out.println("_____Testing_____");
            System.out.print("Passenger: " + passenger.name + ", Flight: "+ passenger.flight +
                    ", From: " + passenger.from + ", To: " + passenger.to + ", Seat " + passenger.seat +
                    ", Code: " + passenger.code + "\n");
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

    public void checkIn(String code) {
        boolean found = false;
        for (Traveler passenger : list) {
            if (code.equalsIgnoreCase(passenger.code)) {
                System.out.print("Passenger: " + passenger.name + ", Flight: " + passenger.flight +
                        ", From: " + passenger.from + ", To: " + passenger.to + ", Seat " + passenger.seat + "\n");
                found = true;
            }
        }
        if (!found) {
            System.out.println(code + " doesn't exist.");
        } else {
            System.out.println("Type 'Confirm'");
            String confirm = sc.next();
            if (confirm.equals("confirm")) {
                deleteConfirmationNumber(code);
                System.out.println("Check-in Successfully!");
            } else {
                System.out.println("BOT?!");
            }
        }
    }

    public void deleteConfirmationNumber(String code) {
        boolean found = false;
        for (Traveler passenger : list) {
            if (code.equalsIgnoreCase(passenger.code)){
                delete.add(passenger);
            }
            found = true;
        }
        if (!found){
            System.out.println(code + " doesn't exist.");
        }
        if(found) {
            for (Traveler removePassenger : delete) {
                list.remove(removePassenger);
            }
            delete.clear();
        }
    }
}
