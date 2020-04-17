package homework2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Genre {

    private Long id;
    private String description;

    public Genre(String description) {
        this.description = description;
    }
}
