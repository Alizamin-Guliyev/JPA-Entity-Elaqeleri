package az.iktex.organization.structure.testders.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "country", length = 50)
    private String country;

    // CascadeType.ALL + orphanRemoval: müəllif silinəndə onun author_book sətirləri də silinir, kitablar isə qalır (AuthorBook-dan Book-a cascade yoxdur).
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthorBook> authorBooks = new ArrayList<>();

    public void addBook(Book book) {
        if (book != null) {
            AuthorBook authorBook = new AuthorBook();
            authorBook.setAuthor(this);
            authorBook.setBook(book);
            authorBooks.add(authorBook);
            book.getAuthorBooks().add(authorBook);
        }
    }

    public void removeBook(Book book) {
        if (book != null) {
            Iterator<AuthorBook> iterator = authorBooks.iterator();
            while (iterator.hasNext()) {
                AuthorBook authorBook = iterator.next();
                if (authorBook.getBook() == book) {
                    iterator.remove();
                    book.getAuthorBooks().remove(authorBook);
                }
            }
        }
    }
}
