package com.bhagwan.FirstSbProject.Controller;

import com.bhagwan.FirstSbProject.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalApplication {

    private Map<Long,JournalEntity> journalEntityMap = new HashMap<>();

    @GetMapping("/getall")
    public List<JournalEntity> getAll(){
        return new ArrayList<>(journalEntityMap.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntity myEntry){
        journalEntityMap.put(myEntry.getId(),myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntity getJournalEntryById(@PathVariable Long myId){
        return journalEntityMap.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntity deleteJournalEntryById(@PathVariable Long myId){
        return journalEntityMap.remove(myId);
    }
    @PutMapping("/id/{myid}")
    public JournalEntity updateJournalById(@PathVariable Long myid, @RequestBody JournalEntity myEntry){
        return journalEntityMap.put(myid,myEntry);
    }

}

