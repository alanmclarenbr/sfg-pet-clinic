package guru.springframework.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2441302184792880252L;
    private Long id;

}
