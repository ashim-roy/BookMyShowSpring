package com.scaler.bookmyshow.services;

package com.scaler.bookmyshowoct23.services;

import com.scaler.bookmyshow.models.Show;
import com.scaler.bookmyshow.models.ShowSeat;
import com.scaler.bookmyshow.models.ShowSeatType;
import com.scaler.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {
    private ShowSeatTypeRepository showSeatTypeRepository;   // private instance variable

    @Autowired
    PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(List<ShowSeat> showSeats, Show show) {  // to find price we need list of showseats will also have type, show details
        /*
        In price calculator: we have list of showseats, we will check price of each seats. For that show we first will check the price of all the types of seats.
         If 4 seats type, before finding the price of booking we first fetch from DB what is the price of every type for seat in this particular show.
         */
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);  // list of show seat types = show seat repo. Find by show and pass show object here.
        int amount = 0;
        for (ShowSeat showSeat : showSeats) {
            for (ShowSeatType showSeatType : showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }

        return amount;
    }
}
