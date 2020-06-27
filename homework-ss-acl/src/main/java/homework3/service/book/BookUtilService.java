package homework3.service.book;

import homework3.domain.Book;

public interface BookUtilService {

    void showAll();
    void showInstance(Long id);
    void delete(Book book);
    void add(String title);
    void update(Long id, String title);
}
