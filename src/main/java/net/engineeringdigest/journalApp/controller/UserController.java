package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user){
        User userInDb = userService.findByUsername(username);
        if(userInDb == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // User not found
        }
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        System.out.println(userInDb.getId());
        System.out.println(userInDb.getUsername());
        System.out.println(userInDb.getPassword());
        userService.saveEntry(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}

