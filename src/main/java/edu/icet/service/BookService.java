package edu.icet.service;

import edu.icet.model.dto.Book;
import edu.icet.model.entity.BookEntity;
import edu.icet.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
