package guru.springframework.sfgpetclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
public class Vet extends Person {

    private Set<Specialty> specialties;
}
