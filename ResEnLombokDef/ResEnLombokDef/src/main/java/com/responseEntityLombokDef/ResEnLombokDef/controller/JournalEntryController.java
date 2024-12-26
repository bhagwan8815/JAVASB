package com.responseEntityLombokDef.ResEnLombokDef.controller;

import com.responseEntityLombokDef.ResEnLombokDef.entity.JournalEntry;
import com.responseEntityLombokDef.ResEnLombokDef.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entry")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryServices;

    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
       try {
           myEntry.setDate(LocalDateTime.now());
           journalEntryServices.saveEntry(myEntry);
           return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

    }


    @GetMapping("/check")
    public String checkAll(){
        return "all is okay";
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry>all = journalEntryServices.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId){
       Optional<JournalEntry> journalEntry = journalEntryServices.findById(myId);
       if(journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId){
        journalEntryServices.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryServices.findById(myId).orElse(null);

        if(old!=null){
            old.setName(newEntry.getName()!=null && !newEntry.getName().equals("") ? newEntry.getName() :old.getName());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent() :old.getContent());

            journalEntryServices.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
