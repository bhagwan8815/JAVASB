package com.mongodbintegration.mognodbInteExample.controller;

import com.mongodbintegration.mognodbInteExample.entity.JournalEntry;
import com.mongodbintegration.mognodbInteExample.services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entry")
public class JournalEntryController {
   @Autowired
    private JournalEntryServices journalEntryServices;

   @PostMapping("/create")
    public boolean createEntry(@RequestBody JournalEntry myEntry){
       myEntry.setDate(LocalDateTime.now());
       journalEntryServices.saveEntry(myEntry);
       return true;
   }


   @GetMapping("/check")
   public String checkAll(){
       return "all is okay";
   }

   @GetMapping
    public List<JournalEntry> getAll(){
      return journalEntryServices.getAll();
   }

   @GetMapping("id/{myId}")
    public Optional<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
       return journalEntryServices.findById(myId); //if we return only JournalEntry , then we have to write findById(myId).orElse(null)
   }

   @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId){
       journalEntryServices.deleteById(myId);
       return true;
   }

   @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
       JournalEntry old = journalEntryServices.findById(myId).orElse(null);

       if(old!=null){
           old.setName(newEntry.getName()!=null && !newEntry.getName().equals("") ? newEntry.getName() :old.getName());
           old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent() :old.getContent());
       }
       journalEntryServices.saveEntry(old);
       return old;
   }

}
