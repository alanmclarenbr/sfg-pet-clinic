package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static guru.springframework.sfgpetclinic.model.PetType.builder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetTypeSDJpaServiceTest {

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private PetTypeSDJpaService petTypeSDJpaService;

    @Test
    void shouldReturnWhenFindAll() {
        PetType dog = builder()
                .name("Dog")
                .build();

        PetType cat = builder()
                .name("Cat")
                .build();

        when(petTypeRepository.findAll()).thenReturn(Set.of(dog, cat));
        Set<PetType> findAll = petTypeSDJpaService.findAll();

        assertThat(findAll).hasSize(2);
        assertThat(findAll).containsExactlyInAnyOrder(dog, cat);
    }

    @Test
    void shouldReturnWhenFindById() {
        PetType dog = builder()
                .id(1L)
                .name("Dog")
                .build();

        when(petTypeRepository.findById(dog.getId())).thenReturn(Optional.of(dog));

        assertThat(petTypeSDJpaService.findById(dog.getId())).isEqualTo(dog);
    }

    @Test
    void shouldReturnWhenSave() {
        PetType dog = builder()
                .name("Dog")
                .build();

        when(petTypeRepository.save(dog)).thenReturn(dog);

        assertThat(petTypeSDJpaService.save(dog)).isEqualTo(dog);
    }

    @Test
    void verifyDeleteInteractionWhenDelete() {
        PetType dog = builder()
                .name("Dog")
                .build();

        petTypeSDJpaService.delete(dog);

        verify(petTypeRepository).delete(eq(dog));
    }

    @Test
    void verifyDeleteByIdInteractionWhenDelete() {
        petTypeSDJpaService.deleteById(1L);

        verify(petTypeRepository).deleteById(eq(1L));
    }
}