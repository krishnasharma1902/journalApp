package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){
        User user = userService.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if(journalEntries != null && !journalEntries.isEmpty()){
         return new ResponseEntity<>(journalEntries, HttpStatus.OK);
     }
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username){
        try {
            System.out.println("Hi");
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String myId){
        Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("id/{username}/{Id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String Id, @PathVariable String username){
            journalEntryService.deleteEntryById(Id, username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/id/{username}/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable String username, @PathVariable String myId, @RequestBody JournalEntry journalEntry){
        JournalEntry existingEntry = journalEntryService.getEntryById(myId).orElse(null);
        if(existingEntry != null){
            existingEntry.setTitle(journalEntry.getTitle());
            existingEntry.setContent(journalEntry.getContent());
            journalEntryService.saveEntry(existingEntry);
            return new ResponseEntity<>(existingEntry, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
