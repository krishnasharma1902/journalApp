package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveEntry(User user){
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
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

    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public void deleteUserById(UUID id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String userName){
        return userRepository.findByUsername(userName);
    }
}
