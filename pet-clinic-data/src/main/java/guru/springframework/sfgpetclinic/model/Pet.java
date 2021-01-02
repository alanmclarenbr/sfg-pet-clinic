package guru.springframework.sfgpetclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class Pet extends BaseEntity{
    private Owner owner;
    private PetType petType;
    private LocalDate birthDate;
}
