package dev.vlad.sport.auth;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, ObjectId> {

    UserDocument findByUsername(String username);

}
