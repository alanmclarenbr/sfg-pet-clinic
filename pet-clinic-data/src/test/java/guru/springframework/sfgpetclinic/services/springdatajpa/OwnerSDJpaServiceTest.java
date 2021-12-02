package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static guru.springframework.sfgpetclinic.model.Owner.builder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerSDJpaService ownerSDJpaService;

    @Test
    void shouldReturnWhenFindAll() {
        Owner alan = builder()
                .firstName("Alan")
                .lastName("Santo")
                .build();

        Owner elis = builder()
                .firstName("Elis")
                .lastName("Santos")
                .build();

        when(ownerRepository.findAll()).thenReturn(Set.of(alan, elis));
        Set<Owner> findAll = ownerSDJpaService.findAll();

        assertThat(findAll).hasSize(2);
        assertThat(findAll).containsExactlyInAnyOrder(alan, elis);
    }

    @Test
    void shouldReturnWhenFindById() {
        Owner alan = builder()
                .id(1L)
                .firstName("Alan")
                .lastName("Santo")
                .build();

        when(ownerRepository.findById(alan.getId())).thenReturn(Optional.of(alan));

        assertThat(ownerSDJpaService.findById(alan.getId())).isEqualTo(alan);
    }

    @Test
    void shouldReturnWhenFindByLastName() {
        Owner alan = builder()
                .firstName("Alan")
                .lastName("Santo")
                .build();

        when(ownerRepository.findByLastName("Santo")).thenReturn(alan);

        assertThat(ownerSDJpaService.findByLastName("Santo")).isEqualTo(alan);
    }

    @Test
    void shouldReturnWhenSave() {
        Owner alan = builder()
                .firstName("Alan")
                .lastName("Santo")
                .build();

        when(ownerRepository.save(alan)).thenReturn(alan);

        assertThat(ownerSDJpaService.save(alan)).isEqualTo(alan);
    }

    @Test
    void verifyDeleteInteractionWhenDelete() {
        Owner alan = builder()
                .firstName("Alan")
                .lastName("Santo")
                .build();

        ownerSDJpaService.delete(alan);

        verify(ownerRepository).delete(eq(alan));
    }

    @Test
    void verifyDeleteByIdInteractionWhenDelete() {
        ownerSDJpaService.deleteById(1L);

        verify(ownerRepository).deleteById(eq(1L));
    }
}