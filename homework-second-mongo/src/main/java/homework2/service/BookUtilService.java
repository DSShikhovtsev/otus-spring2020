package homework2.service;

public interface BookUtilService {

    void showAll();
    void showInstance(String id);
    void delete(String id);
    void add(String title);
    void update(String id, String title);
}
