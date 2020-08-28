package webflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @Field(name = "comment")
    private String comment;

    @DBRef
    private Book book;

    public Comment(String id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(String comment, Book book) {
        this.comment = comment;
        this.book = book;
    }
}
