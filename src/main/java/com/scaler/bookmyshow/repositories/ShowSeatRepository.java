package com.scaler.bookmyshow.repositories;
import com.scaler.bookmyshow.models.Show;
import com.scaler.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> { // deals with data access and database operations
    @Override
    List<ShowSeat> findAllById(List<Long> showSeatIDs);  //  This method overrides the findAllById method of the JpaRepository. It takes a list of Long IDs as input and returns a list of ShowSeat entities with those IDs.

   // @Override
   // Optional<ShowSeat> findById(Long aLong);  // we dont need it

    @Override
    ShowSeat save(ShowSeat showSeat); // This method overrides the save method of the JpaRepository. It takes a ShowSeat entity as input and returns the saved ShowSeat entity.


    /*
    Insert -> If showSeat object is not present in the DB.
           -> If input showSeat object doesn't contain the id.

    Update -> updating the existing object in the DB.

    //save method returns an updated object.
     */


}
