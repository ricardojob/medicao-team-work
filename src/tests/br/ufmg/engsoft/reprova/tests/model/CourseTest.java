package br.ufmg.engsoft.reprova.tests.model;

import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.model.Student;
import br.ufmg.engsoft.reprova.model.variability.CoarseGrainedCourseFactory;
import br.ufmg.engsoft.reprova.model.variability.FineGrainedCourseFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseTest {
    @Test
    void testCreateWithStudents_FineGrained() {
        float score1 = 50.0f;
        Student student1 = new Student("id1", score1);
        float score2 = 49.0f;
        Student student2 = new Student("id2", score2);
        CourseFactory factory = new FineGrainedCourseFactory();
        Course course = factory.createCourse(2019, Course.Reference._1, "Software Reuse", Arrays.asList(student1, student2));
        assertEquals(course.getScore(),(score1+score2)/2);
    }

    @Test
    void testCreateWithScore_CoarseGrained() {
        CourseFactory factory = new CoarseGrainedCourseFactory();
        Course course = factory.createCourse(2019, Course.Reference._1, "Software Reuse", 50.0f);
        assertEquals(course.getScore(),50.0f);
    }
    
    @Test
    void testCreateWithScore_FineGrained() {
        CourseFactory factory = new FineGrainedCourseFactory();
        assertThrows(
                IllegalArgumentException.class,
                ()->factory.createCourse(2019, Course.Reference._1, "Software Reuse", 50.0f)
        );
    }

    @Disabled
    @Test
    void testCreateWithStudents_CoarseGrained() {
        float score1 = 50.0f;
        Student student1 = new Student("id1", score1);
        float score2 = 49.0f;
        Student student2 = new Student("id2", score2);
        CourseFactory factory = new CoarseGrainedCourseFactory();
        assertThrows(
                IllegalArgumentException.class,
                ()->factory.createCourse(2019, Course.Reference._1, "Software Reuse", Arrays.asList(student1, student2))
        );
    }
}
