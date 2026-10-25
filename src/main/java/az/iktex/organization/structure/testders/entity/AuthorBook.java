package az.iktex.organization.structure.testders.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Tapşırıq 3 ilə müqayisə:
// Üstünlük: ara cədvəl ayrıca entity olduğu üçün ona əlavə sütunlar (məsələn rol, əlavə tarixi) qoymaq və onu birbaşa sorğulamaq olur.
// Çatışmazlıq: daha çox kod (əlavə entity və helper metodlar) lazımdır, kitablara çatmaq üçün əlavə addım (author.getAuthorBooks() -> getBook()) atılır.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author_book",
        uniqueConstraints = @UniqueConstraint(name = "uk_author_book",
                columnNames = {"author_id", "book_id"}))
public class AuthorBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Cascade yoxdur: author_book sətri silinəndə müəllif silinməməlidir.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ab_author"))
    private Author author;

    // Cascade yoxdur: author_book sətri silinəndə kitab silinməməlidir.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ab_book"))
    private Book book;
}
