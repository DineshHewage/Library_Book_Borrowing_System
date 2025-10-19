package edu.icet.service;

import edu.icet.model.dto.Book;
import edu.icet.model.entity.BookEntity;
import edu.icet.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void add (Book book){
        BookEntity bookEntity = new BookEntity(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getIsbn(),
                book.getCategory(),
                book.getAvailableCopies()
        );
        bookRepository.save(bookEntity);
    }

    public List<Book> getAllDetail() {
//--------Fetch all data from DB
        List<BookEntity> all = bookRepository.findAll();
//--------Create an empty list to store Book DTO objects
        List<Book> books = new ArrayList<>();
//--------Co
        for (BookEntity bookEntity : all) {
            books.add(new Book(
                    bookEntity.getId(),
                    bookEntity.getTitle(),
                    bookEntity.getAuthor(),
                    bookEntity.getPublisher(),
                    bookEntity.getIsbn(),
                    bookEntity.getCategory(),
                    bookEntity.getAvailableCopies()
            ));
        }
        return books;
    }

    public Book searchByID(String id) {
//        Find the book by id
        Optional<BookEntity> byId = bookRepository.findById(Long.parseLong(id));
//        Handle the exception where book is not found
        BookEntity bookEntity = byId.orElseThrow(()-> new RuntimeException("Book not found with ID: " + id));
//        Convert BookEntity to BookDTO and return
        return new Book(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getPublisher(),
                bookEntity.getIsbn(),
                bookEntity.getCategory(),
                bookEntity.getAvailableCopies()
        );
    }

    public void updateBook(Book book) {
//        Check if the book exists
        BookEntity existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + book.getId()));

//        Update fields
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setCategory(book.getCategory());
        existingBook.setAvailableCopies(book.getAvailableCopies());

//        Save updated book
        bookRepository.save(existingBook);
    }

    public void deleteBook(String bookId) {
//        Check if the book exists
        bookRepository.deleteById(Long.parseLong(bookId));
    }
}
