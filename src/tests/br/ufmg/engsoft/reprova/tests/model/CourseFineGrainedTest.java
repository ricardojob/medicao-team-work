package br.ufmg.engsoft.reprova.tests.model;

import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.model.Student;
import br.ufmg.engsoft.reprova.model.variability.FineGrainedCourseFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseFineGrainedTest extends CourseGrainedTest {
    @Test
    void testCreateWithStudents_FineGrained() {
        float score1 = 50.0f;
        Student student1 = new Student("id1", score1);
        float score2 = 49.0f;
        Student student2 = new Student("id2", score2);
//        CourseFactory factory = getFactory();
        Course course = factory.createCourse(2019, Course.Reference._1, "Software Reuse", Arrays.asList(student1, student2));
        assertEquals(course.getScore(),(score1+score2)/2);
    }

    @Test
    void testCreateWithScore_FineGrained() {
//        CourseFactory factory = getFactory();
        assertThrows(
                IllegalArgumentException.class,
                ()->factory.createCourse(2019, Course.Reference._1, "Software Reuse", 50.0f)
        );
    }
    protected CourseFactory getFactory() {
        return new FineGrainedCourseFactory();
    }
}
