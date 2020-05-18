package homework2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Author {

    private Long id;
    private String name;

    public Author(String name) {
        this.name = name;
    }
}
