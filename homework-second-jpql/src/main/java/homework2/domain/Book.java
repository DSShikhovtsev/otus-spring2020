package homework2.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @BatchSize(size = 10)
    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinTable(name = "authors_books", joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author"))
    private List<Author> authors = new ArrayList<>();

    @BatchSize(size = 10)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private List<Genre> genres = new ArrayList<>();

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(String title) {
        this.title = title;
    }
}
