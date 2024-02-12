package tn.esprit.spring;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
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

     @Test
     void testUpdateInstructor() {
         Instructor instructor = new Instructor(); // create an instructor object

         // Mocking the behavior of the repository
         when(instructorRepository.save(instructor)).thenReturn(instructor);

         // Call the method and verify the behavior
         Instructor updatedInstructor = instructorServices.updateInstructor(instructor);

         // Assert the result
         assertEquals(instructor, updatedInstructor);

         // Verify that the save method of the repository was called once
         verify(instructorRepository, times(1)).save(instructor);
     }

     @Test
     void testRetrieveInstructor() {
         long numInstructor = 1L;

         // Mocking the behavior of the repository
         when(instructorRepository.findById(numInstructor)).thenReturn(Optional.of(new Instructor()));

         // Call the method and verify the behavior
         Instructor retrievedInstructor = instructorServices.retrieveInstructor(numInstructor);

         // Assert the result
         assertNotNull(retrievedInstructor);

         // Verify that the findById method of the repository was called once
         verify(instructorRepository, times(1)).findById(numInstructor);
     }
     @Test
     void testAddInstructorAndAssignToCourse() {
         Instructor instructor = new Instructor(); // create an instructor object
         long numCourse = 1L;

         Course course = new Course(); // create a course object

         // Mocking the behavior of the repository
         when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));
         when(instructorRepository.save(instructor)).thenReturn(instructor);

         // Call the method and verify the behavior
         Instructor result = instructorServices.addInstructorAndAssignToCourse(instructor, numCourse);

         // Assert the result
         assertNotNull(result);
         assertTrue(result.getCourses().contains(course));

         // Verify that the findById and save methods of the repositories were called once each
         verify(courseRepository, times(1)).findById(numCourse);
         verify(instructorRepository, times(1)).save(instructor);
     }
}
