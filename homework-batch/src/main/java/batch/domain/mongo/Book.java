package batch.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("books")
public class Book {

    @Id
    private String id;

    @Field (name = "title")
    private String title;

    @DBRef
    private List<Author> authors = new ArrayList<>();

    @DBRef
    private List<Genre> genres = new ArrayList<>();

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(String title) {
        this.title = title;
    }
}
