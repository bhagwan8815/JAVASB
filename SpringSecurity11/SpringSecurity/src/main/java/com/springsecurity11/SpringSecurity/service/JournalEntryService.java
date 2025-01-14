package com.springsecurity11.SpringSecurity.service;

import com.springsecurity11.SpringSecurity.entity.JournalEntry;
import com.springsecurity11.SpringSecurity.entity.User;
import com.springsecurity11.SpringSecurity.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;  //it is called object inject .

    @Autowired
    private UserServices userServices;


    //    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userServices.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userServices.saveUser(user);

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry", e);
        }

    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){

        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id,String userName){
        User user = userServices.findByUserName(userName);
       boolean removed= user.getJournalEntries().removeIf(x -> x.getId().equals(id));

        if(removed){
            userServices.saveUser(user);
            journalEntryRepository.deleteById(id);

        }

    }
}
