package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import service.BookService;
import service.MockBookService;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    @Qualifier("book")
    private BookService bookService;

    public BookController (BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    public List<Book> getList() {
        return bookService.getBooks();
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return this.bookService.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PutMapping()
    @ResponseBody
    public void  updateBook(@RequestBody Book book) {
        bookService.update(book);
    }
}
