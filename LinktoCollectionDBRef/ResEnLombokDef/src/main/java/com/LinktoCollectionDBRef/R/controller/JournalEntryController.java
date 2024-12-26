package com.LinktoCollectionDBRef.R.controller;

import com.LinktoCollectionDBRef.R.entity.JournalEntry;
import com.LinktoCollectionDBRef.R.entity.User;
import com.LinktoCollectionDBRef.R.services.JournalEntryService;
import com.LinktoCollectionDBRef.R.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private UserServices userServices;

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
       try {

           journalEntryServices.saveEntry(myEntry,userName);
           return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

    }


    @GetMapping("/check")
    public String checkAll(){
        return "all is okay";
    }

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userServices.findByUserName(userName);
        List<JournalEntry>all = user.getJournalEntries();
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

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId,@PathVariable String userName){
        journalEntryServices.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntryById(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName
    ){
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
