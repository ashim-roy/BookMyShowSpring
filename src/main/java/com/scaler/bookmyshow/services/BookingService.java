package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.exceptions.InvalidUserException;
import com.scaler.bookmyshow.exceptions.ShowSeatNotAvailableException;
/*
import jakarta.transaction.Transactional;
import com.scaler.bookmyshow.models.ShowSeat;
import com.scaler.bookmyshow.models.ShowSeatStatus;
*/
import com.scaler.bookmyshow.models.*;
import com.scaler.bookmyshow.repositories.ShowRepository;
import com.scaler.bookmyshow.repositories.ShowSeatRepository;
import com.scaler.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service //you can also write @Controller or @Component
public class BookingService {
    private UserRepository userRepository;  // injecting here
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculator priceCalculator;
    @Autowired
    BookingService(UserRepository userRepository, ShowRepository showRepository,
                   ShowSeatRepository showSeatRepository, PriceCalculator priceCalculator){   // constructor injection
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculator = priceCalculator;
    }

    @Transactional(isolation = Isolation.)
    // So if u use @Transactional annotation on a method, whatever DB queries you are going to write, all the queries are going to be there inside the DB lock. All those queries are going to acquire a database lock internally.
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIDs) throws Exception{

        //Steps:-
        /*
        -----------TAKE DB LOCK-------------
        1. Get the user from userId.
        2. Get the show from showId.
        3. Get the showSeats from list of showSeatIds.
        4. Check if all the show Seats are available.
        ---------TAKE DB LOCK----------- (We'll not do this.)
        5. If yes, Mark the show seat status as BLOCKED.
        6. Save the updated status to DB.
        ----------RELEASE THE DB LOCK-------
        7. Create the Booking Object. (Go to the Payments page.)
        8. Return the Booking object.
        ----------RELEASE THE DB LOCK----------------
         */
        //1. Get the user from userId.
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new InvalidUserException("Invalid User");
        }
        User bookedBy = optionalUser.get();

        //2. Get the show from showId.
        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalShow.isEmpty()) {
            throw new Exception("Invalid Show");
        }
        Show show = optionalShow.get();

        //3. Get the showSeats from list of showSeatIds.
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIDs);

        //4. Check if all the show Seats are available.
        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotAvailableException("ShowSeat not available");
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();

        //5. If yes, Mark the show seat status as BLOCKED.

        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }

      //6. Save the updated status to DB.
        for (ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        //7. Create the Booking Object.
        Booking booking = new Booking();
        //set all the parameters user
        booking.setUser(bookedBy);
        booking.setShow(show);  // here we set the show object
        booking.setCreatedAt(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setPayments(new ArrayList<>());
        booking.setAmount(priceCalculator.calculatePrice(savedShowSeats, show));

        return booking; // and finally we return booking object.
    }
}
