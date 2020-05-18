package homework2.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.bson.BSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "addAuthors", author = "temp")
    public void insertAuthors(DB db) {
        DBCollection collection = db.getCollection("authors");
        collection.insert(new BasicDBObject().append("name", "Petya"),
                new BasicDBObject().append("name", "Kolya"),
                new BasicDBObject().append("name", "Oleg"),
                new BasicDBObject().append("name", "Dasha"));
    }

    @ChangeSet(order = "002", id = "addBooks", author = "temp")
    public void insertBooks(DB db) {
        DBCollection collection = db.getCollection("books");
        collection.insert(new BasicDBObject().append("title", "About sea")
                        .append("authors", new BasicDBObject().append("name", "Kolya").append("name", "Dasha"))
                        .append("genres", new BasicDBObject().append("description", "History").append("description", "Nature").append("description", "Mystic").append("description", "Legend").append("description", "Animals")),
                new BasicDBObject().append("title", "About Stalin")
                        .append("authors", new BasicDBObject().append("name", "Petya").append("name", "Dasha"))
                        .append("genres", new BasicDBObject().append("description", "History")),
                new BasicDBObject().append("title", "About pigs")
                        .append("authors", new BasicDBObject().append("name", "Kolya").append("name", "Dasha"))
                        .append("genres", new BasicDBObject().append("description", "Nature").append("description", "Animals")),
                new BasicDBObject().append("title", "About Witcher")
                        .append("authors", new BasicDBObject().append("name", "Dasha"))
                        .append("genres", new BasicDBObject().append("description", "History").append("description", "Nature").append("description", "Animals")),
                new BasicDBObject().append("title", "About ghosts")
                        .append("authors", new BasicDBObject().append("name", "Oleg").append("name", "Dasha"))
                        .append("genres", new BasicDBObject().append("description", "Mystic").append("description", "Legend")),
                new BasicDBObject().append("title", "About magic")
                        .append("authors", new BasicDBObject().append("name", "Petya").append("name", "Dasha"))
                        .append("genres", new BasicDBObject().append("description", "History").append("description", "Mystic")),
                new BasicDBObject().append("title", "About cook")
                        .append("authors", new BasicDBObject().append("name", "Kolya").append("name", "Oleg").append("name", "Dasha"))
                        .append("genres", new BasicDBObject().append("description", "Nature").append("description", "Animals")));
    }

    @ChangeSet(order = "003", id = "addGenres", author = "temp")
    public void insertGenres(DB db) {
        DBCollection collection = db.getCollection("genres");
        collection.insert(new BasicDBObject().append("description", "History"),
                new BasicDBObject().append("description", "Nature"),
                new BasicDBObject().append("description", "Mystic"),
                new BasicDBObject().append("description", "Legend"),
                new BasicDBObject().append("description", "Animals"));
    }

    @ChangeSet(order = "004", id = "addComments", author = "temp")
    public void insertComments(DB db) {
        DBCollection collection = db.getCollection("comments");
        collection.insert(new BasicDBObject().append("comment", "Comment 1")
                        .append("book", new BasicDBObject().append("title", "About sea")
                                .append("authors", "Kolya").append("authors", "Dasha")
                                .append("description", "History").append("genres", "Nature").append("genres", "Mystic").append("genres", "Legend").append("genres", "Animals")),
                new BasicDBObject().append("comment", "Comment 2")
                        .append("book", new BasicDBObject().append("title", "About Stalin")
                                .append("authors", "Petya").append("authors", "Dasha")
                                .append("description", "History")),
                new BasicDBObject().append("comment", "Comment 3")
                        .append("book", new BasicDBObject().append("title", "About pigs")
                                .append("authors", "Kolya").append("authors", "Dasha")
                                .append("description", "Nature").append("genres", "Animals")),
                new BasicDBObject().append("comment", "Comment 4")
                        .append("book", new BasicDBObject().append("title", "About Witcher")
                                .append("authors", "Dasha")
                                .append("description", "History").append("genres", "Nature").append("genres", "Animals")),
                new BasicDBObject().append("comment", "Comment 5")
                        .append("book", new BasicDBObject().append("title", "About ghosts")
                                .append("authors", "Oleg").append("authors", "Dasha")
                                .append("description", "Mystic").append("genres", "Legend")),
                new BasicDBObject().append("comment", "Comment 6")
                        .append("book", new BasicDBObject().append("title", "About magic")
                                .append("authors", "Petya").append("authors", "Dasha")
                                .append("description", "History").append("description", "Mystic")),
                new BasicDBObject().append("comment", "Comment 7")
                        .append("book", new BasicDBObject().append("title", "About cook")
                                .append("authors", new BasicDBObject().append("name", "Kolya").append("name", "Oleg").append("name", "Dasha"))
                                .append("genres", new BasicDBObject().append("description", "Nature").append("description", "Animals"))));
    }
}
