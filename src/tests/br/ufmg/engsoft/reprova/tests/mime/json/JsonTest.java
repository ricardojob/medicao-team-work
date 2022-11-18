package br.ufmg.engsoft.reprova.tests.mime.json;

import br.ufmg.engsoft.reprova.Configuration;
import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {

    /**
     * Rendering then parsing should produce an equivalent Question object.
     */
    @Test
    void testQuestionSerialization_CoarseGrained() {
        Configuration.setCoarseGrained();
        CourseFactory factory = CourseFactory.create();
        Course c1 = factory.createCourse(2019, Course.Reference._1, "Software Reuse", 50.0f);
        Course c2 = factory.createCourse(2019, Course.Reference._1, "Design and Analysis of Algorithms", 49.5f);
        Course c3 = factory.createCourse(2020, Course.Reference._2, "Database", 51.2f);
        Question question = new Question.Builder()
                .id("id")
                .theme("theme")
                .description("description")
                .statement("statement")
                .courses(Arrays.asList(c1, c2, c3))
                .isPrivate(false)
                .build();

        Question questionCopy = getQuestion(question);

        assertEquals(question, questionCopy);
    }

    /**
     * Rendering then parsing should produce an equivalent Question object.
     */
    @Test
    void testQuestionSerialization_FineGrained() {
        Configuration.setFineGrained();
        Student s1 = new Student("id1", 50.0f);
        Student s2 = new Student("id2", 49.0f);
        CourseFactory factory = CourseFactory.create();
        Course c1 = factory.createCourse(2019, Course.Reference._1, "Software Reuse", Arrays.asList(s1, s2));
        Course c2 = factory.createCourse(2019, Course.Reference._1, "Design and Analysis of Algorithms", Arrays.asList(s1, s2));
        Course c3 = factory.createCourse(2020, Course.Reference._2, "Database", Arrays.asList(s1));
        Question question = new Question.Builder()
                .id("id")
                .theme("theme")
                .description("description")
                .statement("statement")
                .courses(Arrays.asList(c1, c2, c3))
                .isPrivate(false)
                .build();

        Question questionCopy = getQuestion(question);

        assertEquals(question, questionCopy);
    }

    private Question getQuestion(Question question) {
        Json formatter = new Json();

        String json = formatter.render(question);

        Question questionCopy = formatter
                .parse(json, Question.Builder.class)
                .build();
        return questionCopy;
    }

    /**
     * Rendering then parsing should produce an equivalent Course object.
     */
    @Test
    void testCourseSerialization_CoarseGrained() {
        Configuration.setCoarseGrained();
        CourseFactory factory = CourseFactory.create();
        Course course = factory.createCourse(2019, Course.Reference._1, "Software Reuse", 50.0f);

        Json formatter = new Json();

        String json = formatter.render(course);

        Course courseCopy = formatter
                .parse(json, CoarseGrainedCourse.class);

        assertEquals(course, courseCopy);
    }

    /**
     * Rendering then parsing should produce an equivalent Course object.
     */
    @Test
    void testCourseSerialization_FineGrained() {
        Configuration.setFineGrained();
        CourseFactory factory = CourseFactory.create();
        Student s1 = new Student("id1", 50.0f);
        Student s2 = new Student("id2", 49.0f);
        Course course = factory.createCourse(2019, Course.Reference._1, "Software Reuse", Arrays.asList(s1, s2));

        Json formatter = new Json();

        String json = formatter.render(course);

        Course courseCopy = formatter
                .parse(json, FineGrainedCourse.class);

        assertEquals(course, courseCopy);
    }

    /**
     * Rendering then parsing should produce an equivalent Student object.
     */
    @Test
    void testStudentSerialization() {
        Student student = new Student("id", 50.0f);

        Json formatter = new Json();

        String json = formatter.render(student);

        Student studentCopy = formatter
                .parse(json, Student.class);

        assertEquals(student, studentCopy);
    }
}
