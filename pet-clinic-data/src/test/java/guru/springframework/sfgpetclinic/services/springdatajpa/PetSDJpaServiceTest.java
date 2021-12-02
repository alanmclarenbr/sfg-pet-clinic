package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetSDJpaService petSDJpaService;

    @Test
    void shouldReturnWhenFindAll() {
        Pet charlie = Pet.builder()
                .name("Charlie")
                .build();

        Pet jake = Pet.builder()
                .name("Jake")
                .build();

        when(petRepository.findAll()).thenReturn(Set.of(charlie, jake));
        Set<Pet> findAll = petSDJpaService.findAll();

        assertThat(findAll).hasSize(2);
        assertThat(findAll).containsExactlyInAnyOrder(charlie, jake);
    }

    @Test
    void shouldReturnWhenFindById() {
        Pet jake = Pet.builder()
                .name("Jake")
                .build();

        when(petRepository.findById(1L)).thenReturn(Optional.of(jake));

        assertThat(petSDJpaService.findById(1L)).isEqualTo(jake);
    }

    @Test
    void shouldReturnWhenSave() {
        Pet jake = Pet.builder()
                .name("Jake")
                .build();

        when(petRepository.save(jake)).thenReturn(jake);

        assertThat(petSDJpaService.save(jake)).isEqualTo(jake);
    }

    @Test
    void verifyDeleteInteractionWhenDelete() {
        Pet jake = Pet.builder()
                .name("Jake")
                .build();

        petSDJpaService.delete(jake);

        verify(petRepository).delete(eq(jake));
    }

    @Test
    void verifyDeleteByIdInteractionWhenDelete() {
        petSDJpaService.deleteById(1L);

        verify(petRepository).deleteById(eq(1L));
    }

}