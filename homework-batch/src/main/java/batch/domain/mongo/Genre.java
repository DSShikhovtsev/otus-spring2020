package batch.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "genres")
public class Genre {

    @Id
    private String id;

    @Field(name = "description")
    private String description;

    public Genre(String description) {
        this.description = description;
    }
}
