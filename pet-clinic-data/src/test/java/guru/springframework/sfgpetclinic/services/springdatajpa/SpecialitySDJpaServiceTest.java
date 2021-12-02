package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static guru.springframework.sfgpetclinic.model.Speciality.builder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    private SpecialityRepository specialityRepository;

    @InjectMocks
    private SpecialitySDJpaService specialitySDJpaService;

    @Test
    void shouldReturnWhenFindAll() {
        Speciality mammals = builder()
                .description("Mammals")
                .build();

        Speciality birds = builder()
                .description("Birds")
                .build();

        when(specialityRepository.findAll()).thenReturn(Set.of(mammals, birds));
        Set<Speciality> findAll = specialitySDJpaService.findAll();

        assertThat(findAll).hasSize(2);
        assertThat(findAll).containsExactlyInAnyOrder(mammals, birds);
    }

    @Test
    void shouldReturnWhenFindById() {
        Speciality mammals = builder()
                .id(1L)
                .description("Mammals")
                .build();

        when(specialityRepository.findById(mammals.getId())).thenReturn(Optional.of(mammals));

        assertThat(specialitySDJpaService.findById(mammals.getId())).isEqualTo(mammals);
    }

    @Test
    void shouldReturnWhenSave() {
        Speciality mammals = builder()
                .description("Mammals")
                .build();

        when(specialityRepository.save(mammals)).thenReturn(mammals);

        assertThat(specialitySDJpaService.save(mammals)).isEqualTo(mammals);
    }

    @Test
    void verifyDeleteInteractionWhenDelete() {
        Speciality mammals = builder()
                .description("Mammals")
                .build();

        specialitySDJpaService.delete(mammals);

        verify(specialityRepository).delete(eq(mammals));
    }

    @Test
    void verifyDeleteByIdInteractionWhenDelete() {
        specialitySDJpaService.deleteById(1L);

        verify(specialityRepository).deleteById(eq(1L));
    }
}