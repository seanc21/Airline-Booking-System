package com.company;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Booking BookingInfo = new Booking();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                                        
                    Welcome to Beyond's Airline Reservation System.
                    What do you want to do?
                    1) Book a flight
                    2) Check-in
                    3) Change flight
                    4) Refund flight""");
            String choice = sc.next();
            if (choice.equals("1")) {
                System.out.println("""
                        These are the available flights
                        * Birmingham BHM <- 6h 23m -> Belize City BZE
                        * Memphis MEM <- 2h 57m -> New York JFK""");
                System.out.println("Roundtrip or Oneway?");
                String flight = sc.next().toLowerCase();
                if (flight.equals("roundtrip")) {
                    System.out.println("Where are you traveling from?(Enter Airport)");
                    String where = sc.next();
                    System.out.println("Where are you traveling to?(Enter Airport)");
                    String to = sc.next();
                    BookingInfo.rightFlight(where, to);
                    System.out.println("""
                                                         _______
                                                        /       \\
                                                       /         \\
                                                      /           \\
                             First Class 1-4 A & B  <|             |>
                             Main Cabin 5-12 A & B   | [A]  1  [B] |
                                                     | [A]  2  [B] |
                                                     | [A]  3  [B] |
                                        _____        |_[A]__4__[B]_|       _____
                                       /  |  \\       | [A]  5  [B] |      /  |  \\
                               _______/___|___\\______| [A]  6  [B] |_____/___|___\\_______
                              /                      | [A]  7  [B] |                      \\
                             /_______________________| [A]  8  [B] |_______________________\\
                            /________________________| [A]  9  [B] |________________________\\
                                                     | [A]  10 [B] |
                                                     | [A]  11 [B] |
                                                     | [A]  12 [B] |
                            """);
                    BookingInfo.unavailableSeats();
                    System.out.println("How many travelers?");
                    int travelers = sc.nextInt();
                    String code = Booking.confirmationNumber();
                    BookingInfo.travelerInfo(travelers, flight, where, to, code);
                    System.out.println("Your confirmation number is (" + code + "). Thanks for booking a flight on Beyond's Airline\n");
                    BookingInfo.viewTicket(code);
                    BookingInfo.viewTickets();
                } else if (flight.equals("oneway")) {
                    System.out.println("Where are you traveling to");
                    String to = sc.next();

                } else {
                    System.out.println("Pick Roundtrip or Oneway! \n");
                }
            } else if (choice.equals("2")) {
                while (true) {
                    System.out.println("What is your confirmation number? [q to quit]");
                    String code = sc.next();
                    if (code.equals("q")) {
                        break;
                    } else {
                        BookingInfo.checkIn(code);
                    }
                }
            } else {
                System.out.println("Choose a number 1-4!\n");
            }
        }
    }
}
