package guru.springframework.sfgpetclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;
    private Pet pet;
}
