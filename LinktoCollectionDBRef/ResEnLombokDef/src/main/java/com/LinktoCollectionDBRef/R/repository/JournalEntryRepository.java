package com.LinktoCollectionDBRef.R.repository;

import com.LinktoCollectionDBRef.R.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
