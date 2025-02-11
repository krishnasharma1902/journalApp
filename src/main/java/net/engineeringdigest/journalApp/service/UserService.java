package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class); -> Can be done using @Slf4j


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void saveEntry(User user){
        userRepository.save(user);
    }

//    @Transactional
    public void saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }catch(Exception e){
//            log.error("Cannot save new user : {}",user.getUsername());
//            log.error("Exception stacktrace : ", e);
            log.error("hahahaha");
            log.warn("hahahaha");
            log.info("hahahaha");
            log.debug("hahahaha");
            log.trace("hahahaha");
        }
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

    public List<String> getUserNamesForSA(String userName){
        List<String> usersForSA = userRepository.getUserNamesForSA();
        return usersForSA;
    }

    public List<User> getUserByUsernameAndEmail(String username, String email){
        System.out.println("Username : "+username+" Email : "+email);
        List<User> userByUsernameAndEmail = userRepository.getUsersByUserNameAndEmail(username, email);
        System.out.println(userByUsernameAndEmail.size());
        return userByUsernameAndEmail;
    }


}
