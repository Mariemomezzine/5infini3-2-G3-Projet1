package tn.esprit.spring;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


 class InstructorServicesImplTest {

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private InstructorServicesImpl instructorServices;

    public InstructorServicesImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddInstructor() {
        Instructor mockInstructor = new Instructor();

        when(instructorRepository.save(any(Instructor.class))).thenReturn(mockInstructor);

        Instructor result = instructorServices.addInstructor(mockInstructor);

        assertNotNull(result);
        assertEquals(mockInstructor, result);

        verify(instructorRepository, times(1)).save(mockInstructor);
    }

    @Test
    void testRetrieveAllInstructors() {
        List<Instructor> mockInstructorList = new ArrayList<>();

        when(instructorRepository.findAll()).thenReturn(mockInstructorList);

        List<Instructor> result = instructorServices.retrieveAllInstructors();

        assertNotNull(result);
        assertEquals(mockInstructorList, result);

        verify(instructorRepository, times(1)).findAll();
    }



}
