package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-all-journals")
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if(journalEntries != null && !journalEntries.isEmpty()){
         return new ResponseEntity<>(journalEntries, HttpStatus.OK);
     }
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-journal-entry")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-entry-by-id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Integer myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries().stream().filter(x -> x.getId() == myId).collect(Collectors.toList());
        for(JournalEntry j : journalEntries) System.out.println(j.getId());
        if(journalEntries != null && !journalEntries.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.getEntryById(myId);
            if(journalEntry.isPresent())
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-entry-by-id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable Integer myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(journalEntryService.deleteEntryById(myId, username))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("update-entry-by-id/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable Integer myId, @RequestBody JournalEntry newEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries().stream().filter(x -> x.getId() == myId).collect(Collectors.toList());
        for(JournalEntry j : journalEntries) System.out.println(j.getId());
        if(journalEntries != null && !journalEntries.isEmpty()){
            JournalEntry journalEntry = journalEntryService.getEntryById(myId).get();
            journalEntry.setTitle(newEntry.getTitle());
            journalEntry.setContent(newEntry.getContent());
            journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
