package com.responseEntityLombokDef.ResEnLombokDef.repository;

import com.responseEntityLombokDef.ResEnLombokDef.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
