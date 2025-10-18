package edu.icet.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookEntity {
    @Id
    private long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String category;
    private int availableCopies;
}
