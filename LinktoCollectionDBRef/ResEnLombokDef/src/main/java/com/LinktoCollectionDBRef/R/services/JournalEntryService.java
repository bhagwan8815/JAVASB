package com.LinktoCollectionDBRef.R.services;

import com.LinktoCollectionDBRef.R.entity.User;
import com.LinktoCollectionDBRef.R.repository.JournalEntryRepository;
import com.LinktoCollectionDBRef.R.entity.JournalEntry;
import com.LinktoCollectionDBRef.R.repository.UserRepository;
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


    public void saveEntry(JournalEntry journalEntry,String userName){
      User user = userServices.findByUserName(userName);
      journalEntry.setDate(LocalDateTime.now());
      JournalEntry saved = journalEntryRepository.save(journalEntry);
      user.getJournalEntries().add(saved);
      userServices.saveEntry(user);

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
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userServices.saveEntry(user);
        journalEntryRepository.deleteById(id);

    }

}
