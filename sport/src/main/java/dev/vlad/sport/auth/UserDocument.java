package dev.vlad.sport.auth;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDocument {

    @Id
    private ObjectId id;

    private String username;
    private String passwordHash;

    private Boolean isAdmin;

}
