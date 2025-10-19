package edu.icet.Controller;

import edu.icet.model.dto.Book;
import edu.icet.model.entity.BookEntity;
import edu.icet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/title")
    public String getTitle(){
        return "Madol Duwa";
    }

//    @GetMapping("/{author}")
//    public String getAuthor(@PathVariable String author){
//        //System.out.println(author);
//        return author;
//    }

    @PostMapping("/addBook")
    public String add(@RequestBody Book book){
        bookService.add(book);
        return "Book added successfully!";
    }

//    @GetMapping("/book/{id}")
//    public Book getBookById(@PathVariable long id){
//        return new Book(id,"Madol Duwa","Martin Wickramasinghe","Lake House","12345","Novel",10);
//    }

    @GetMapping("/all")
    public List<Book> getAllDetails(){
         return bookService.getAllDetail();
    }

    @GetMapping("/search/{bookId}")
    public Book searchBookById(@PathVariable String  bookId){
        return bookService.searchByID(bookId);
    }

    @PutMapping("/update")
    public String updateBook(@RequestBody Book book){
        bookService.updateBook(book);
        return "Book updated successfully!";
    }

    @DeleteMapping("delete/{bookId}")
    public String deleteBookById(@PathVariable String bookId){
        bookService.deleteBook(bookId);
        return "Book deleted successfully!";
    }
}
