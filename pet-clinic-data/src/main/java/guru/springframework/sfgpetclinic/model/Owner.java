package guru.springframework.sfgpetclinic.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = ALL, mappedBy = "owner")
    private Set<Pet> pets;
}
