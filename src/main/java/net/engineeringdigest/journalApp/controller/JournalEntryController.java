package net.engineeringdigest.journalApp.controller;

import ch.qos.logback.classic.joran.JoranConfigurator;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("journalApp")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    

    @GetMapping("/getAll")
    public List<JournalEntry> getAll(){
     return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable String myId){
        return journalEntryService.getEntryById(myId).orElse(null);
    }

    @DeleteMapping("id/{Id}")
    public String deleteEntryById(@PathVariable String Id){
        journalEntryService.deleteEntryById(Id);
        return "Deleted";
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateEntryById(@PathVariable String myId, @RequestBody JournalEntry journalEntry){
        JournalEntry existingEntry = journalEntryService.getEntryById(myId).orElse(null);
        if(existingEntry != null){
            existingEntry.setTitle(journalEntry.getTitle());
            existingEntry.setContent(journalEntry.getContent());
        }
        journalEntryService.saveEntry(existingEntry);
        return existingEntry;
    }

}
