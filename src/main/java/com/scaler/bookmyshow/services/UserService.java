package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.User;
import com.scaler.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    //create an object of user repository..
    private UserRepository userRepository;

    //create its constructor.
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }
    private User login(String email, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email); //Fetching User by Email:

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();  // Password Encoding:

        // Checking User Existence:

        User user = null;

        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }
// It checks if the Optional contains a user. If it does, it assigns the user to the user variable. If not, user remains null.

        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            return user;
        }
        throw new RuntimeException("Incorrect password..");  // if find we return user elase error..

    }



    public User signUp(String email, String password) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
            return login(email, password);
        }  //else i will create a user

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        //save it in database
        User savedUser = userRepository.save(user);
        return savedUser;

    }
}
