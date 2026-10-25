package az.iktex.organization.structure.testders.entity;

import az.iktex.organization.structure.testders.entity.converter.GenderConverter;
import az.iktex.organization.structure.testders.enums.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Convert(converter = GenderConverter.class)
    @Column(name = "gender_id", nullable = false)
    private Gender gender;

    // CascadeType.ALL + orphanRemoval: user silinəndə pasport da silinir, setPassport(null) və ya əvəzləmədə köhnə pasport bazadan silinir.
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Passport passport;

    public void setPassport(Passport passport) {
        if (this.passport != null && this.passport != passport) {
            this.passport.setUser(null);
        }
        if (passport != null) {
            passport.setUser(this);
        }
        this.passport = passport;
    }
}
