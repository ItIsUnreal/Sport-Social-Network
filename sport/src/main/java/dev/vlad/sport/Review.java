package dev.vlad.sport;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document (collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Review {

    @Id
    private ObjectId id;

    private String body;


    public Review(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(body, review.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body);
    }
}
