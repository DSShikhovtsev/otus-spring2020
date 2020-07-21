package batch.reposinories.mongo;

public interface BookRepositoryCustom {

    void removeAuthorsArrayElementById(String id);
    void removeGenresArrayElementById(String id);
}
