package homework2.service;

public interface BookUtilService {

    void showAll();
    void showInstance(Long id);
    void delete(Long id);
    void add(String title);
    void update(Long id, String title);
}
