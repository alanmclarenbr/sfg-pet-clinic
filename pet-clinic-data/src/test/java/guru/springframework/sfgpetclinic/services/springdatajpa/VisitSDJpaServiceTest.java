package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static guru.springframework.sfgpetclinic.model.Visit.builder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitSDJpaService visitSDJpaService;

    @Test
    void shouldReturnWhenFindAll() {
        Visit charlie = builder()
                .description("Charlie")
                .build();

        Visit jake = builder()
                .description("Jake")
                .build();

        when(visitRepository.findAll()).thenReturn(Set.of(charlie, jake));
        Set<Visit> findAll = visitSDJpaService.findAll();

        assertThat(findAll).hasSize(2);
        assertThat(findAll).containsExactlyInAnyOrder(charlie, jake);
    }

    @Test
    void shouldReturnWhenFindById() {
        Visit jake = builder()
                .id(1L)
                .description("Jake")
                .build();

        when(visitRepository.findById(jake.getId())).thenReturn(Optional.of(jake));

        assertThat(visitSDJpaService.findById(jake.getId())).isEqualTo(jake);
    }

    @Test
    void shouldReturnWhenSave() {
        Visit jake = builder()
                .description("Jake")
                .build();

        when(visitRepository.save(jake)).thenReturn(jake);

        assertThat(visitSDJpaService.save(jake)).isEqualTo(jake);
    }

    @Test
    void verifyDeleteInteractionWhenDelete() {
        Visit jake = builder()
                .description("Jake")
                .build();

        visitSDJpaService.delete(jake);

        verify(visitRepository).delete(eq(jake));
    }

    @Test
    void verifyDeleteByIdInteractionWhenDelete() {
        visitSDJpaService.deleteById(1L);

        verify(visitRepository).deleteById(eq(1L));
    }
}