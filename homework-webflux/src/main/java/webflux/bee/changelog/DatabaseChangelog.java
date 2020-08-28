package webflux.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import webflux.domain.Author;
import webflux.domain.Book;
import webflux.domain.Comment;
import webflux.domain.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@ChangeLog
@Component
public class DatabaseChangelog {

    private Author petya;
    private Author kolya;
    private Author oleg;
    private Author dasha;

    private Genre history;
    private Genre nature;
    private Genre mystic;
    private Genre legend;
    private Genre animals;

    private Book sea;
    private Book stalin;
    private Book pigs;
    private Book witcher;
    private Book ghosts;
    private Book magic;
    private Book cook;

    @ChangeSet(order = "001", id = "addAuthors", author = "temp")
    public void insertAuthors(MongoTemplate template) {
        petya = template.save(new Author("Petya"));
        kolya = template.save(new Author("Kolya"));
        oleg = template.save(new Author("Oleg"));
        dasha = template.save(new Author("Dasha"));
    }

    @ChangeSet(order = "002", id = "addGenres", author = "temp")
    public void insertGenres(MongoTemplate template) {
        history = template.save(new Genre("History"));
        nature = template.save(new Genre("Nature"));
        mystic = template.save(new Genre("Mystic"));
        legend = template.save(new Genre("Legend"));
        animals = template.save(new Genre("Animals"));
    }

    @ChangeSet(order = "003", id = "addBooks", author = "temp")
    public void insertBooks(MongoTemplate template) {
        sea = new Book("About sea");
        sea.getAuthors().add(kolya);
        sea.getAuthors().add(dasha);
        sea.getGenres().add(history);
        sea.getGenres().add(nature);
        sea.getGenres().add(mystic);
        sea.getGenres().add(legend);
        sea.getGenres().add(animals);
        sea = template.save(sea);

        stalin = new Book("About Stalin");
        stalin.getAuthors().add(petya);
        stalin.getAuthors().add(dasha);
        stalin.getGenres().add(history);
        stalin = template.save(stalin);

        pigs = new Book("About pigs");
        pigs.getAuthors().add(kolya);
        pigs.getAuthors().add(dasha);
        pigs.getGenres().add(nature);
        pigs.getGenres().add(animals);
        pigs = template.save(pigs);

        witcher = new Book("About Witcher");
        witcher.getAuthors().add(dasha);
        witcher.getGenres().add(history);
        witcher.getGenres().add(nature);
        witcher.getGenres().add(animals);
        witcher = template.save(witcher);

        ghosts = new Book("About ghosts");
        ghosts.getAuthors().add(oleg);
        ghosts.getAuthors().add(dasha);
        ghosts.getGenres().add(mystic);
        ghosts.getGenres().add(legend);
        ghosts = template.save(ghosts);

        magic = new Book("About magic");
        magic.getAuthors().add(petya);
        magic.getAuthors().add(dasha);
        magic.getGenres().add(history);
        magic.getGenres().add(mystic);
        magic = template.save(magic);

        cook = new Book("About cook");
        cook.getAuthors().add(kolya);
        cook.getAuthors().add(oleg);
        cook.getAuthors().add(dasha);
        cook.getGenres().add(nature);
        cook.getGenres().add(animals);
        cook = template.save(cook);
    }

    @ChangeSet(order = "004", id = "addComments", author = "temp")
    public void insertComments(MongoTemplate template) {
        template.save(new Comment("Comment 1", sea));
        template.save(new Comment("Comment 2", stalin));
        template.save(new Comment("Comment 3", pigs));
        template.save(new Comment("Comment 4", witcher));
        template.save(new Comment("Comment 5", ghosts));
        template.save(new Comment("Comment 6", magic));
        template.save(new Comment("Comment 7", cook));
    }
}
