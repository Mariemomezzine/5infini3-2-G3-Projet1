package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PisteServicesImplTest {

    @Mock
    IPisteRepository pisteRepository;

    @InjectMocks
    PisteServicesImpl pisteServices;

    @Test
    public void testRetrieveAllPistes() {
        List<Piste> mockPistes = new ArrayList<>();
        mockPistes.add(new Piste(1L, "Piste A", Color.GREEN, 1000, 20, null));
        mockPistes.add(new Piste(2L, "Piste B", Color.BLUE, 1500, 25, null));

        when(pisteRepository.findAll()).thenReturn(mockPistes);

        List<Piste> retrievedPistes = pisteServices.retrieveAllPistes();

        assertEquals(2, retrievedPistes.size());
    }

    @Test
    public void testAddPiste() {
        Piste pisteToAdd = new Piste(null, "Piste C", Color.RED, 1200, 30, null);
        Piste savedPiste = new Piste(3L, "Piste C", Color.RED, 1200, 30, null);

        when(pisteRepository.save(pisteToAdd)).thenReturn(savedPiste);

        Piste addedPiste = pisteServices.addPiste(pisteToAdd);

        assertEquals(3L, addedPiste.getNumPiste());
        assertEquals("Piste C", addedPiste.getNamePiste());
    }

    @Test
    public void testRemovePiste() {
        Long numPisteToRemove = 1L;

        pisteServices.removePiste(numPisteToRemove);

        verify(pisteRepository, times(1)).deleteById(numPisteToRemove);
    }

    @Test
    public void testRetrievePiste() {
        Long numPisteToRetrieve = 1L;
        Piste pisteToRetrieve = new Piste(1L, "Piste A", Color.GREEN, 1000, 20, null);

        when(pisteRepository.findById(numPisteToRetrieve)).thenReturn(Optional.of(pisteToRetrieve));

        Piste retrievedPiste = pisteServices.retrievePiste(numPisteToRetrieve);

        assertEquals("Piste A", retrievedPiste.getNamePiste());
    }
}
