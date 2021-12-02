package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static guru.springframework.sfgpetclinic.model.Vet.builder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    @Mock
    private VetRepository vetRepository;

    @InjectMocks
    private VetSDJpaService vetSDJpaService;

    @Test
    void shouldReturnWhenFindAll() {
        Vet alan = builder()
                .firstName("Alan")
                .lastName("Santo")
                .build();

        Vet elis = builder()
                .firstName("Elis")
                .lastName("Santos")
                .build();

        when(vetRepository.findAll()).thenReturn(Set.of(alan, elis));
        Set<Vet> findAll = vetSDJpaService.findAll();

        assertThat(findAll).hasSize(2);
        assertThat(findAll).containsExactlyInAnyOrder(alan, elis);
    }

    @Test
    void shouldReturnWhenFindById() {
        Vet alan = builder()
                .id(1L)
                .firstName("Alan")
                .lastName("Santo")
                .build();

        when(vetRepository.findById(alan.getId())).thenReturn(Optional.of(alan));

        assertThat(vetSDJpaService.findById(alan.getId())).isEqualTo(alan);
    }

    @Test
    void shouldReturnWhenSave() {
        Vet alan = builder()
                .firstName("Alan")
                .lastName("Santo")
                .build();

        when(vetRepository.save(alan)).thenReturn(alan);

        assertThat(vetSDJpaService.save(alan)).isEqualTo(alan);
    }

    @Test
    void verifyDeleteInteractionWhenDelete() {
        Vet alan = builder()
                .firstName("Alan")
                .lastName("Santo")
                .build();

        vetSDJpaService.delete(alan);

        verify(vetRepository).delete(eq(alan));
    }

    @Test
    void verifyDeleteByIdInteractionWhenDelete() {
        vetSDJpaService.deleteById(1L);

        verify(vetRepository).deleteById(eq(1L));
    }

}