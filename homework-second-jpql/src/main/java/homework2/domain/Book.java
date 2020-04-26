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


    @OneToMany(targetEntity = Genre.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<Genre> genres = new ArrayList<>();

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(String title) {
        this.title = title;
    }
}
