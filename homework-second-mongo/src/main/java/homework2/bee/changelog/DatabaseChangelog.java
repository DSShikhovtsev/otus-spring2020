package homework2.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.bson.BSONObject;

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
                        .append("authors", "Kolya").append("authors", "Dasha")
                        .append("genres", "History").append("genres", "Nature").append("genres", "Mystic").append("genres", "Legend").append("genres", "Animals")
                        .append("comments", "Comment 1"),
                new BasicDBObject().append("title", "About Stalin")
                        .append("authors", "Petya").append("authors", "Dasha")
                        .append("genres", "History")
                        .append("comments", "Comment 2"),
                new BasicDBObject().append("title", "About pigs")
                        .append("authors", "Kolya").append("authors", "Dasha")
                        .append("genres", "Nature").append("genres", "Animals")
                        .append("comments", "Comment 3"),
                new BasicDBObject().append("title", "About Witcher")
                        .append("authors", "Dasha")
                        .append("genres", "History").append("genres", "Nature").append("genres", "Animals")
                        .append("comments", "Comment 4"),
                new BasicDBObject().append("title", "About ghosts")
                        .append("authors", "Oleg").append("authors", "Dasha")
                        .append("genres", "Mystic").append("genres", "Legend")
                        .append("comments", "Comment 5"),
                new BasicDBObject().append("title", "About magic")
                        .append("authors", "Petya").append("authors", "Dasha")
                        .append("genres", "History").append("genres", "Mystic")
                        .append("comments", "Comment 6"),
                new BasicDBObject().append("title", "About cook")
                        .append("authors", "Kolya").append("authors", "Oleg").append("authors", "Dasha")
                        .append("genres", "Nature").append("genres", "Animals")
                        .append("comments", "Comment 7"));
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
        collection.insert(new BasicDBObject().append("comment", "Comment 1"),
                new BasicDBObject().append("comment", "Comment 2"),
                new BasicDBObject().append("comment", "Comment 3"),
                new BasicDBObject().append("comment", "Comment 4"),
                new BasicDBObject().append("comment", "Comment 5"),
                new BasicDBObject().append("comment", "Comment 6"),
                new BasicDBObject().append("comment", "Comment 7"));
    }
}
