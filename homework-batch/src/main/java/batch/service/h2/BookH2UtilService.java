package batch.service.h2;

public interface BookH2UtilService {

    void showAll();
    void showInstance(Long id);
    void delete(Long id);
    void add(String title);
    void update(Long id, String title);
}
