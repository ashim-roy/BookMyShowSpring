package com.scaler.bookmyshow.repositories;

import com.scaler.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//For every table, user, show, showseat.. will be created a repository. Every entity ideally will have a repo
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // SQL query come

    @Override

   //    @Query(select * from )  to modify..
    Optional<User> findById(Long aLong);
    //select * from user where id = aLong

    Optional<User> findByEmail(String email);
    //select * from user where email_id = email;

    //save in DB

    @Override
    User save(User user);


    //JPA (Java Persistence API) Repository.
//Create a Repository:
//1. Class -> Interface.
//2. extend -> JPARepository
}
