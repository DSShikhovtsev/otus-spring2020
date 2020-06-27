DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authors_books;
DROP TABLE IF EXISTS books_genres;
DROP TABLE IF EXISTS books_comments;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS acl_sid;
DROP TABLE IF EXISTS acl_class;
DROP TABLE IF EXISTS acl_entry;
DROP TABLE IF EXISTS acl_object_identity;

CREATE TABLE users(id BIGSERIAL PRIMARY KEY, username VARCHAR(255), password VARCHAR(255));
CREATE TABLE authors(id BIGSERIAL PRIMARY KEY, name VARCHAR(255));
CREATE TABLE books(id BIGSERIAL PRIMARY KEY, title VARCHAR(255));
CREATE TABLE genres(id BIGSERIAL PRIMARY KEY, description VARCHAR(255));
CREATE TABLE comments(id BIGSERIAL PRIMARY KEY, comment VARCHAR(255));
CREATE TABLE authors_books(id_author BIGINT NOT NULL, id_book BIGINT NOT NULL,
    FOREIGN KEY(id_author) REFERENCES authors(id) ON DELETE CASCADE,
    FOREIGN KEY(id_book) REFERENCES books(id) ON DELETE CASCADE
);
CREATE TABLE books_genres(id_book BIGINT NOT NULL, id_genre BIGINT NOT NULL,
    FOREIGN KEY(id_book) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY(id_genre) REFERENCES genres(id) ON DELETE CASCADE
);
CREATE TABLE books_comments(id_book BIGINT NOT NULL, id_comment BIGINT NOT NULL,
    FOREIGN KEY(id_book) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY(id_comment) REFERENCES comments(id) ON DELETE CASCADE
);

ALTER TABLE authors_books ADD PRIMARY KEY(id_author, id_book);
ALTER TABLE books_genres ADD PRIMARY KEY(id_book, id_genre);
ALTER TABLE books_comments ADD PRIMARY KEY(id_book, id_comment);

CREATE TABLE IF NOT EXISTS acl_sid (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  principal tinyint(1) NOT NULL,
  sid varchar(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_1 (sid,principal)
);

CREATE TABLE IF NOT EXISTS acl_class (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  class varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_2 (class)
);

CREATE TABLE IF NOT EXISTS acl_entry (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  acl_object_identity bigint(20) NOT NULL,
  ace_order int(11) NOT NULL,
  sid bigint(20) NOT NULL,
  mask int(11) NOT NULL,
  granting tinyint(1) NOT NULL,
  audit_success tinyint(1) NOT NULL,
  audit_failure tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_4 (acl_object_identity,ace_order)
);

CREATE TABLE IF NOT EXISTS acl_object_identity (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  object_id_class bigint(20) NOT NULL,
  object_id_identity bigint(20) NOT NULL,
  parent_object bigint(20) DEFAULT NULL,
  owner_sid bigint(20) DEFAULT NULL,
  entries_inheriting tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_uk_3 (object_id_class,object_id_identity)
);

ALTER TABLE acl_entry
ADD FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity(id);

ALTER TABLE acl_entry
ADD FOREIGN KEY (sid) REFERENCES acl_sid(id);

--
-- Constraints for table acl_object_identity
--
ALTER TABLE acl_object_identity
ADD FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (object_id_class) REFERENCES acl_class (id);

ALTER TABLE acl_object_identity
ADD FOREIGN KEY (owner_sid) REFERENCES acl_sid (id);