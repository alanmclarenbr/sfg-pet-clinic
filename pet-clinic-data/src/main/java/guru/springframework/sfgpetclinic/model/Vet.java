package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @Builder
    public Vet(String firstName, String lastName, Set<Speciality> specialties) {
        super(firstName, lastName);
        this.specialties = specialties;
    }

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "vet_specialities",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialties;
}
