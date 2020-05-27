package homework2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinTable(name = "books_comments", joinColumns = @JoinColumn(name = "id_comment"),
            inverseJoinColumns = @JoinColumn(name = "id_book"))
    private Book book;

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }
}
