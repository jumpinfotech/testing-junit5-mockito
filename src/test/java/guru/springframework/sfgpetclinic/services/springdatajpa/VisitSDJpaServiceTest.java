package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // initialises Mocks
class VisitSDJpaServiceTest {

    // we mostly call the service + verify the repository in this test
    @Mock
    VisitRepository visitRepository;

    // Create an instance of service
    // + inject mocks into it's constructor.
    // Here visitRepository (@Mock) will be injected into it.
    // Each test gets a new instance of service + mock.
    @InjectMocks
    VisitSDJpaService service;

    // optional with JUnit 5 to give it a better name:
    @DisplayName("Test Find All")
    @Test
    void findAll() {
        Visit visit = new Visit();

        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        when(visitRepository.findAll()).thenReturn(visits);

        // returns a Set
        Set<Visit> foundVisits = service.findAll();

        verify(visitRepository).findAll();

        assertThat(foundVisits).hasSize(1);

    }

    @Test
    void findById() {
        // instance we will return back
        Visit visit = new Visit();

        // when findById is called with anyLong then return visit wrapped in an Optional:-
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

        // service called which then calls the repository method:
        Visit foundVisit = service.findById(1L);

        // visitRepository.findById method is called once with any long value.
        verify(visitRepository).findById(anyLong());

        // assertThat = assertj
        // returned foundVisit isNotNull
        assertThat(foundVisit).isNotNull();
    }

    @Test
    void save() {
        Visit visit = new Visit();

        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        Visit savedVisit = service.save(new Visit());

        // argument matchers:
        // Use any, when the value calling it is not our focus.
        // Verify it's called with any Visit class object:-
        verify(visitRepository).save(any(Visit.class));
        // any .... intellij suggestion matcher options e.g. anyLong()

        assertThat(savedVisit).isNotNull();
    }

    @Test
    void delete() {
        Visit visit = new Visit();

        service.delete(visit);

        verify(visitRepository).delete(any(Visit.class));

    }

    @Test
    void deleteById() {
        // visitSDJpaService.deleteById needs a long:
        service.deleteById(1L);
        // verify = how many interactions a mock receives.
        // static import verify, by default wantedNumberOfInvocations = 1,
        // therefore calling service.deleteById(1L) twice will cause this to fail:
        verify(visitRepository).deleteById(anyLong()); // passes
        // atLeastOnce() - passes
        verify(visitRepository, atLeastOnce()).deleteById(anyLong());
        // not called with 5L - passes
        verify(visitRepository, never()).deleteById(5L);
        // maxNumberOfInvocations = 5, 5 passes, 6 would fail
        verify(visitRepository, atMost(5)).deleteById(anyLong());
        // called twice expected, but it was called only once - fails
        verify(visitRepository, times(2)).deleteById(anyLong());
    }
}