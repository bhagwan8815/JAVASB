package com.springsecurity11.SpringSecurity.repository;

import com.springsecurity11.SpringSecurity.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
