package com.solvd.airport;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.airport.dao.impl.*;
import com.solvd.airport.models.*;
import com.solvd.airport.services.impl.*;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        AirlineDAO airlineDAO = new AirlineDAO();
        AirportDAO airportDAO = new AirportDAO();
        FlightDAO flightDAO = new FlightDAO();
        LuggageDAO luggageDAO = new LuggageDAO();
        PassengerDAO passengerDAO = new PassengerDAO();
        RoleDAO roleDAO = new RoleDAO();
        StaffDAO staffDAO = new StaffDAO();
        TicketDAO ticketDAO = new TicketDAO();

        AirportService airportService = new AirportService(airportDAO);
        FlightService flightService = new FlightService(flightDAO, airlineDAO);
        PassengerService passengerService = new PassengerService(passengerDAO, ticketDAO, luggageDAO);
        StaffService staffService = new StaffService(staffDAO, roleDAO);
        TicketService ticketService = new TicketService(ticketDAO);

        // create Airline, Airports
        Airline airline = new Airline();
        airline.setName("Garden Beds");
        airlineDAO.add(airline);

        Airport airport_a = new Airport();
        airport_a.setName("Salat Airport");
        airport_a.setCity("Tomatoland");
        airport_a.setCountry("US of Vegetables");
        airportService.addAirport(airport_a);

        Airport airport_b = new Airport();
        airport_b.setName("Smoothie Airport");
        airport_b.setCity("Berryland");
        airport_b.setCountry("UK of Fruits");
        airportService.addAirport(airport_b);

        // create a Flight
        Flight flight = new Flight();
        flight.setAirlineId(airline.getAirlineId());
        flight.setDepartureAirportId(airport_a.getAirportId());
        flight.setArrivalAirportId(airport_b.getAirportId());
        flight.setDepartureTime(LocalDateTime.now().plusDays(1));
        flight.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(7));
        flightService.scheduleFlight(flight);

        // create a passanger
        Passenger pax = new Passenger();
        pax.setName("Ms Pumpkin");
        passengerService.registerPassenger(pax);

        // issue a Ticket
        Ticket ticket = new Ticket();
        ticket.setPassengerId(pax.getPassengerId());
        ticket.setFlightId(flight.getFlightId());
        ticket.setSeatNumber("12A");
        ticket.setPrice(499.99);
        ticketService.bookTicket(ticket);

        // create Luggage
        Luggage bag = new Luggage();
        bag.setPassengerId(pax.getPassengerId());
        bag.setWeight(18.5);
        luggageDAO.add(bag);

        // hire Staff
        Role role = new Role();
        role.setRoleName("Gate Agent");
        roleDAO.add(role);
        Staff staff = new Staff();
        staff.setName("John Peackle");
        staff.setRoleId(role.getRoleId());
        staffService.hireStaff(staff);

        // check
        logger.info("Airline created: id={}", airline.getAirlineId());
        logger.info("Airports: Salat Airport={}, Smoothie Airport={}", airport_a.getAirportId(),
                airport_b.getAirportId());
        logger.info("Flight: id={}", flight.getFlightId());
        logger.info("Passenger: id={}", pax.getPassengerId());
        logger.info("Ticket: id={}, seat={}", ticket.getTicketId(), ticket.getSeatNumber());
        logger.info("Luggage: id={}, weight={}", bag.getLuggageId(), bag.getWeight());
        logger.info("Staff: id={}, roleId={}", staff.getStaffId(), staff.getRoleId());

        // update seat
        ticket.setSeatNumber("14C");
        ticketService.bookTicket(ticket);

        logger.info("Done.");

        // XML parsing
        XmlBookingService bookingService = new XmlBookingService();
        XmlPaymentService paymentService = new XmlPaymentService();

        List<Booking> bookings = bookingService.getBookingsFromXml("src/main/resources/xml/booking.xml");
        List<Payment> payments = paymentService.getPaymentsFromXml("src/main/resources/xml/payment.xml");

        logger.info("Parsed Bookings:");
        bookings.forEach(b -> logger.info(b.toString()));

        logger.info("Parsed Payments:");
        payments.forEach(p -> logger.info(p.toString()));
    }
}