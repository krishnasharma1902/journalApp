package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void saveEntry(User user){
        userRepository.save(user);
    }
    @Transactional
    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }


//
//    public void updateUserByUserName(String oldUsername, User user){
//        System.out.println(oldUsername);
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        userRepository.updateUserByUsername(oldUsername, user.getUsername(), user.getPassword());
//    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String userName){
        return userRepository.findByUsername(userName);
    }
}
