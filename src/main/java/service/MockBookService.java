package service;

import model.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service("book")
public class MockBookService implements BookService {
    private List<Book> list = new ArrayList<>();
    private static Long nextId = 4L;

    public MockBookService() {
        list.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    @Override
    public List<Book> getBooks() {
        return list;
    }

    @Override
    public void add(Book book) {
        book.setId(nextId++);
        list.add(book);
    }

    @Override
    public Optional<Book> get(Long id) {
        return list.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(Long id) {
        if (get(id).isPresent()) {
            list.remove(this.get(id).get());
        }
    }

    @Override
    public void update(Book book) {
        if(this.get(book.getId()).isPresent()) {
            int indexOf = list.indexOf(this.get(book.getId()).get());
            list.set(indexOf,book);
        }
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public static Long getNextId() {
        return nextId;
    }

    public static void setNextId(Long nextId) {
        MockBookService.nextId = nextId;
    }
}
