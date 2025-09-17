package com.bms.BookMyMovie.Service;

import com.bms.BookMyMovie.Dto.*;
import com.bms.BookMyMovie.Entity.*;
import com.bms.BookMyMovie.Exception.ResouceNotFoundException;
import com.bms.BookMyMovie.Exception.SeatUnavailableException;
import com.bms.BookMyMovie.Repository.BookingRepository;
import com.bms.BookMyMovie.Repository.ShowRepository;
import com.bms.BookMyMovie.Repository.ShowSeatsRepository;
import com.bms.BookMyMovie.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private ShowSeatsRepository showSeatsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public BookingsDto createBooking(BookingRequestDto bookingRequest){
        Users user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new ResouceNotFoundException("User Not Found"));

        Shows show = showRepository.findById(bookingRequest.getShowId())
                .orElseThrow(() -> new ResouceNotFoundException("Show Not Found"));

        List<ShowSeats> selectSeats =showSeatsRepository.findAllById(bookingRequest.getSeatIds());

        for(ShowSeats seats : selectSeats){
            if(!"AVAILABLE".equals(seats.getStatus())){
                throw new SeatUnavailableException("Sorry ! Seat "+seats.getSeats().getSeatNumber()+"is not available.");
            }
            seats.setStatus("LOCKED");
        }
        showSeatsRepository.saveAll(selectSeats);

        Double totalAmount = selectSeats.stream()
                .mapToDouble(ShowSeats::getPrice)
                .sum();

        //payment generated
        Payments payment = new Payments();
        payment.setAmount(totalAmount);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod(bookingRequest.getPaymentMethod());
        payment.setStatus("SUCCESS");
        payment.setTransactionId(UUID.randomUUID().toString());

        //booking generated
        Bookings booking = new Bookings();
        booking.setUsers(user);
        booking.setShow(show);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("CONFIRMED");
        booking.setTotalAmount(totalAmount);
        booking.setBookingNumber(UUID.randomUUID().toString());
        booking.setPayment(payment);

        Bookings saveBooking = bookingRepository.save(booking);

        selectSeats.forEach(seats -> {
            seats.setStatus("BOOKED");
            seats.setBooking(saveBooking);
        });

        showSeatsRepository.saveAll(selectSeats);

        return mapToBookingDto(saveBooking , selectSeats);

    }
    public BookingsDto getBookingById(Long id){
        Bookings bookings = bookingRepository.findById(id)
                .orElseThrow(()->new ResouceNotFoundException("Booking Not Found"));
        List<ShowSeats> showSeats = showSeatsRepository.findAll()
                .stream()
                .filter(seats -> seats.getBooking() !=null && seats.getBooking().getId().equals(bookings.getId()))
                .collect(Collectors.toList());

        return mapToBookingDto(bookings , showSeats);

    }
    public BookingsDto getBookingByNumber(String bookingNumber){
        Bookings bookings = bookingRepository.findByBookingNumber(bookingNumber)
                .orElseThrow(()->new ResouceNotFoundException("Booking Not Found"));
        List<ShowSeats> showSeats = showSeatsRepository.findAll()
                .stream()
                .filter(seats -> seats.getBooking() !=null && seats.getBooking().getId().equals(bookings.getId()))
                .collect(Collectors.toList());

        return mapToBookingDto(bookings , showSeats);
    }
    public List<BookingsDto> getBookingByUserId(Long userId){
        List<Bookings> bookings = bookingRepository.findByUserid(userId);
        return bookings.stream()
                .map(bookings1 -> {
                    List<ShowSeats> seats = showSeatsRepository.findAll()
                            .stream().
                            filter(seats1 -> seats1.getBooking() != null && seats1.getBooking().getId().equals(bookings1.getId()))
                            .collect(Collectors.toList());
                    return mapToBookingDto(bookings1 , seats);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingsDto cancelBooking(Long id){
        Bookings bookings = bookingRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Booking Not found"));
        bookings.setStatus("CANCELLED");

        List<ShowSeats> seats = showSeatsRepository.findAll()
                .stream()
                .filter(seats1 -> seats1.getBooking()!= null && seats1.getBooking().getId().equals(bookings.getId()))
                .collect(Collectors.toList());
        seats.forEach(seats1 -> {
            seats1.setStatus("AVAILABLE");
            seats1.setBooking(null);
        });

        if(bookings.getPayment()!= null){
            bookings.getPayment().setStatus("REFUNDED");
        }

        Bookings updatebooking = bookingRepository.save(bookings);
        showSeatsRepository.saveAll(seats);
        return mapToBookingDto(updatebooking , seats);

    }

    //mapping to booking
    private BookingsDto mapToBookingDto(Bookings booking , List<ShowSeats> seats){
        BookingsDto bookingsDto = new BookingsDto();
        bookingsDto.setId(booking.getId());
        bookingsDto.setBookingNumber(booking.getBookingNumber());
        bookingsDto.setBookingTime(booking.getBookingTime());
        bookingsDto.setStatus(booking.getStatus());
        bookingsDto.setTotalAmount(booking.getTotalAmount());

        //Users
        UsersDto usersDto = new UsersDto();
        usersDto.setId(booking.getUsers().getId());
        usersDto.setName(booking.getUsers().getName());
        usersDto.setEmail(booking.getUsers().getEmail());
        usersDto.setPhoneNumber(booking.getUsers().getPhoneNo());
        bookingsDto.setUsers(usersDto);

        //shows & moviesDto
        ShowsDto showsDto = new ShowsDto();
        showsDto.setShowId(booking.getShow().getId());
        showsDto.setStartTime(booking.getShow().getStartTime());
        showsDto.setEndTime(booking.getShow().getEndTime());

        MoviesDto moviesDto = new MoviesDto();
        moviesDto.setMovieId(booking.getShow().getMovies().getId());
        moviesDto.setTitle(booking.getShow().getMovies().getTitle());
        moviesDto.setLanguage(booking.getShow().getMovies().getLanguage());
        moviesDto.setGenre(booking.getShow().getMovies().getGenre());
        moviesDto.setDescription(booking.getShow().getMovies().getDescription());
        moviesDto.setReleaseDate(booking.getShow().getMovies().getReleaseDate());
        moviesDto.setDurationMinutes(booking.getShow().getMovies().getDuration());
        moviesDto.setPosterUrl(booking.getShow().getMovies().getPosterUrl());
        showsDto.setMovies(moviesDto);

        //screen
        ScreensDto screensDto = new ScreensDto();
        screensDto.setScreensId(booking.getShow().getScreen().getId());
        screensDto.setScreenName(booking.getShow().getScreen().getName());
        screensDto.setTotalSeats(booking.getShow().getScreen().getTotalSeats());

        TheatresDto theatresDto = new TheatresDto();
        theatresDto.setTheatreId(booking.getShow().getScreen().getTheatre().getId());
        theatresDto.setTheatreName(booking.getShow().getScreen().getTheatre().getName());
        theatresDto.setAddress(booking.getShow().getScreen().getTheatre().getAddress());
        theatresDto.setCity(booking.getShow().getScreen().getTheatre().getCity());
        theatresDto.setTotalScreens(booking.getShow().getScreen().getTheatre().getTotalScreens());

        screensDto.setTheatres(theatresDto);
        showsDto.setScreensDto(screensDto);
        bookingsDto.setShowDto(showsDto);

        List<ShowSeatsDto> seatsDto = seats.stream().map(seats1 ->{
            ShowSeatsDto seatDto = new ShowSeatsDto();
            seatDto.setId(seats1.getId());
            seatDto.setStatus(seats1.getStatus());
            seatDto.setPrice(seats1.getPrice());

            SeatsDto baseSeatDto = new SeatsDto();
            baseSeatDto.setSeatsId(seats1.getSeats().getId());
            baseSeatDto.setSeatCategory(seats1.getSeats().getSeatCategory());
            baseSeatDto.setSeatNumber(seats1.getSeats().getSeatNumber());
            baseSeatDto.setBasePrice(seats1.getSeats().getBasePrice());
            seatDto.setSeats(baseSeatDto);
            return seatDto;
        })
                .collect(Collectors.toList());
            bookingsDto.setShowSeats(seatsDto);

            if(booking.getPayment() != null){
            PaymentsDto paymentsDto = new PaymentsDto();
            paymentsDto.setId(booking.getPayment().getId());
            paymentsDto.setAmount(booking.getPayment().getAmount());
            paymentsDto.setTransactionID(booking.getPayment().getTransactionId());
            paymentsDto.setPaymentMethod(booking.getPayment().getPaymentMethod());
            paymentsDto.setStatus(booking.getPayment().getStatus());
            paymentsDto.setPaymentTime(booking.getPayment().getPaymentTime());
            bookingsDto.setPayment(paymentsDto);
            }
            return bookingsDto;

    }
}
