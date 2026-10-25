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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "phone", length = 20)
    private String phone;

    // CascadeType.ALL + orphanRemoval: departament silinəndə bütün işçiləri silinir, siyahıdan çıxarılan işçi də bazadan silinir.
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        if (employee != null) {
            employees.add(employee);
            employee.setDepartment(this);
        }
    }

    public void removeEmployee(Employee employee) {
        if (employee != null) {
            employees.remove(employee);
            employee.setDepartment(null);
        }
    }
}
