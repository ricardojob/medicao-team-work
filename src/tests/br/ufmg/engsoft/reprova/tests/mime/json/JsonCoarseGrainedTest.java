package br.ufmg.engsoft.reprova.tests.mime.json;

import br.ufmg.engsoft.reprova.Configuration;
import br.ufmg.engsoft.reprova.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonCoarseGrainedTest extends JsonAbstractTest{

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

        Question questionCopy = parser(question);

        assertEquals(question, questionCopy);
    }

    /**
     * Rendering then parsing should produce an equivalent Course object.
     */
    @Test
    void testCourseSerialization_CoarseGrained() {
        Configuration.setCoarseGrained();
        CourseFactory factory = CourseFactory.create();
        Course course = factory.createCourse(2019, Course.Reference._1, "Software Reuse", 50.0f);

        Course courseCopy = parse(course, CoarseGrainedCourse.class);
        assertEquals(course, courseCopy);
    }
}
