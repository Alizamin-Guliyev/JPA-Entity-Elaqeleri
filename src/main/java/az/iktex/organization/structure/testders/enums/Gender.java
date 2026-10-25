package az.iktex.organization.structure.testders.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE(1),
    FEMALE(2);

    private final Integer id;

    public static Gender fromId(Integer id) {
        for (Gender gender : values()) {
            if (gender.id.equals(id)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown gender id: " + id);
    }
}
