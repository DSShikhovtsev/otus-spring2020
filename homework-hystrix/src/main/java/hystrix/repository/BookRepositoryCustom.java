package hystrix.repository;

public interface BookRepositoryCustom {

    void removeAuthorsArrayElementById(String id);
    void removeGenresArrayElementById(String id);
}
