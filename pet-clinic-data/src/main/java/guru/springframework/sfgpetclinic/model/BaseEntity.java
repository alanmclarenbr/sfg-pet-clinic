package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
class BaseEntity implements Serializable {
    private static final long serialVersionUID = 2441302184792880252L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
}
