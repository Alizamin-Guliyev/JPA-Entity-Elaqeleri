package az.iktex.organization.structure.testders.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true, length = 100)
    private String title;

    @Column(name = "credit", nullable = false)
    private Integer credit;

    @Column(name = "teacher", nullable = false, length = 50)
    private String teacher;

    // Cascade yoxdur: Course inverse tərəfdir, ara cədvəli Student idarə edir; kurs üzərindən əməliyyat (xüsusən silmə) başqa kurslarda da oxuyan tələbələrə yayılmamalıdır.
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();
}
