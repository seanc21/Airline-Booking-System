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
                    4) Refund flight
                    5) Quit""");
            String choice = sc.next();
            if (choice.equals("1")) {
                System.out.println("""
                        These are the available flights
                        * Birmingham BHM <- 6h 23m -> Belize City BZE $351
                        * Memphis MEM <- 2h 57m -> New York JFK $116""");
                while (true) {
                    System.out.println("Roundtrip or Oneway?");
                    String flight = sc.next().toLowerCase();
                    if (flight.equalsIgnoreCase("roundtrip")) {
                        while (true) {
                            System.out.println("Where are you traveling from?(Enter Airport)");
                            String where = sc.next();
                            System.out.println("Where are you traveling to?(Enter Airport)");
                            String to = sc.next();
                            if (BookingInfo.rightFlight(where, to)) {
                                System.out.println("Processing flight!");
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
                                                                 \\_____________/
                                        !!!!! Image only !!!!!
                                        """);
                                BookingInfo.unavailableSeats();
                                System.out.println("How many travelers?");
                                int travelers = sc.nextInt();
                                String code = Booking.confirmationNumber();
                                BookingInfo.travelerInfo(travelers, flight, where, to, code);
                                System.out.println("Your confirmation number is (" + code + "). Thanks for booking a flight on Beyond's Airline\n");
                                BookingInfo.viewTicket(code);
                                BookingInfo.viewBalance(code);
                                BookingInfo.viewTickets();
                                break;
                            } else {
                                System.out.println("Flight couldn't be processed!\n");
                            }
                        }
                    } else if (flight.equalsIgnoreCase("oneway")) {
                        while (true) {
                            String where = "N/A";
                            System.out.println("""
                                    These are the available flights from Memphis
                                    * Oneway Trip --> Birmingham BHM $50
                                    * Oneway Trip --> Belize City BZE $265
                                    * Oneway Trip --> Chicago O'Hare ORD $89
                                    * Oneway Trip --> New York JFK $101""");
                            System.out.println("Where are you traveling to?(Enter Airport)");
                            String to = sc.next();
                            if (BookingInfo.rightFlightOneway(to)) {
                                System.out.println("Processing flight!");
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
                                                                 \\_____________/
                                        !!!!! Image only !!!!!
                                        """);
                                BookingInfo.unavailableSeats();
                                System.out.println("How many travelers?");
                                int travelers = sc.nextInt();
                                String code = Booking.confirmationNumber();
                                BookingInfo.travelerInfo(travelers, flight, where, to, code);
                                System.out.println("Your confirmation number is (" + code + "). Thanks for booking a flight on Beyond's Airline\n");
                                BookingInfo.viewTicket(code);
                                BookingInfo.viewBalance(code);
                                BookingInfo.viewTickets();
                                break;
                            } else {
                                System.out.println("Flight couldn't be processed!\n");
                            }
                        }
                    } else {
                        System.out.println("Pick Roundtrip or Oneway! \n");
                    }
                }
            } else if (choice.equals("2")) {
                while (true) {
                    System.out.println("What is your confirmation code? [q to quit]");
                    String code = sc.next();
                    if (code.equalsIgnoreCase("q")) {
                        break;
                    } else {
                        BookingInfo.checkIn(code);
                    }
                }
            } else if(choice.equals("3")) {
                while (true) {
                    System.out.println("What is your confirmation code? [q to quit]");
                    String code =sc.next();
                    if (BookingInfo.correctCode(code)) {
                        BookingInfo.viewTicketWCode(code);
                        System.out.println("Your about to update your plane ticket!");
                        System.out.println("Who's plane ticket do you want to update?");
                        String update = sc.next();
                        System.out.println("What would you like to update from them?");
                        String updateVariable = sc.next();
                        System.out.println("Input your change!");
                        String change = sc.next();
                        BookingInfo.update(update, updateVariable, change);
                        break;
                    } else if(code.equalsIgnoreCase("q")){
                        break;
                    } else {
                        System.err.println(code + " doesn't exist!");
                    }
                }
//                System.out.println("What is your confirmation code for verification");
//                String code = sc.next();
//                if (Booking.verifyUpdate(code)) {
//                    System.out.println("Working");
//                }
            } else if(choice.equals("4")) {
                while (true) {
                    System.out.println("Are you sure you want a refund. [q to quit]");
                    String answer = sc.next();
                    if (answer.equalsIgnoreCase("yes")) {
                        System.out.println("Please enter your confirmation code to verify its you.");
                        String code = sc.next();
                        BookingInfo.refund(code);
//                        BookingInfo.refundBalance(code);
                        break;
                    } else if (answer.equalsIgnoreCase("no")) {
                        System.out.println("Thanks for staying");
                        break;
                    } else if(answer.equalsIgnoreCase("q")) {
                        break;
                    } else {
                        System.err.println("Answer yes or no, please!");
                    }
                }
            } else if(choice.equals("5")) {
                System.out.println("Exiting program");
                System.exit(0);
            } else {
                System.err.println("Choose a number 1-4!\n");
            }
        }
    }
}
