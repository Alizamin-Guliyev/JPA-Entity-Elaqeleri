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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
@Check(name = "chk_employee_salary", constraints = "salary > 0")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    // BigDecimal, double yox: double ikilik sistemdə saxlanılır və 0.1 + 0.2 kimi hesablamalarda yuvarlaqlaşdırma xətası verir, BigDecimal isə onluq dəyəri dəqiq saxlayır.
    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    // Cascade yoxdur: işçi silinəndə departament silinməməlidir, departament bir çox işçi ilə paylaşılır.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_employee_department"))
    private Department department;
}
