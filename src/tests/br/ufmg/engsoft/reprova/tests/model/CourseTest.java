package br.ufmg.engsoft.reprova.tests.model;

import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.model.FineGrainedCourse;
import br.ufmg.engsoft.reprova.model.Student;
import br.ufmg.engsoft.reprova.model.variability.CoarseGrainedCourseFactory;
import br.ufmg.engsoft.reprova.model.variability.FineGrainedCourseFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;

public class CourseTest {
    @Test
    void testCreateWithStudents_FineGrained() {
        float score1 = 50.0f;
        Student student1 = new Student("id1", score1);
        float score2 = 49.0f;
        Student student2 = new Student("id2", score2);
        CourseFactory factory = new FineGrainedCourseFactory();
        Course course = factory.createCourse(2019, Course.Reference._1, "Software Reuse", Arrays.asList(student1, student2));
        Assertions.assertEquals(course.getScore(),(score1+score2)/2);
    }

    @Test
    void testCreateWithScore_CoarseGrained() {
        CourseFactory factory = new CoarseGrainedCourseFactory();
        Course course = factory.createCourse(2019, Course.Reference._1, "Software Reuse", 50.0f);
        Assertions.assertEquals(course.getScore(),50.0f);
    }
    
    @Test
    void testCreateWithScore_FineGrained() {
        CourseFactory factory = new FineGrainedCourseFactory();
        Assertions.assertThrows(
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
        Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->factory.createCourse(2019, Course.Reference._1, "Software Reuse", Arrays.asList(student1, student2))
        );
    }

    @Test
    void testCreateWithoutStudentsFromBufferedReader() {
        String xml = "<?xml version=\"1.0\" ?>" +
                "<course><year>2022</year>" +
                "<ref>_2</ref>" +
                "<courseName>Software Reuse</courseName>" +
                "<student><id>id1</id><score>50.0</score></student>" +
                "</course>";

        BufferedReader buffer = new BufferedReader(
                new StringReader(xml)
        );

        XStream xstream = new XStream(new StaxDriver());
//        xstream.aliasType("course", Course.class);
        xstream.alias("student", Student.class);
        xstream.alias("course", FineGrainedCourse.class);
        xstream.addImplicitCollection(FineGrainedCourse.class, "students");
//        xstream .addPermission(NoTypePermission.NONE); //forbid everything
//        xstream .addPermission(NullPermission.NULL);   // allow "null"
//        xstream .addPermission(PrimitiveTypePermission.PRIMITIVES); // allow primitive types

        xstream.addPermission(AnyTypePermission.ANY);

        Object expected =  xstream.fromXML(buffer);

        String courseName = "Software Reuse";
        Integer year = 2022;
        Integer referenceInt = 2;
        Course.Reference reference = Course.Reference.fromInt(referenceInt);
        CourseFactory courseFactory = CourseFactory.create();
        Course course = courseFactory.createCourse(year, reference, courseName);

        Assertions.assertEquals(expected,course);
    }
    @Test
    void testCreateWithoutStudents() {
        String courseName = "Software Reuse";
        Integer year = 2022;
        Integer referenceInt = 2;
        Course.Reference reference = Course.Reference.fromInt(referenceInt);
        CourseFactory courseFactory = CourseFactory.create();
        Course course = courseFactory.createCourse(year, reference, courseName);

        XStream xstream = new XStream(new StaxDriver());
        xstream.aliasType("course", Course.class);
        xstream.alias("student", Student.class);
        xstream.addImplicitCollection(FineGrainedCourse.class, "students");
        String xml = xstream.toXML(course);

        String expected = "<?xml version=\"1.0\" ?>" +
                                "<course><year>2022</year>" +
                                    "<ref>_2</ref>" +
                                    "<courseName>Software Reuse</courseName>" +
                                "</course>";

        Assertions.assertEquals(expected,xml);
    }
    @Test
    void testCreateWithStudents() {
        float score1 = 50.0f;
        Student student1 = new Student("id1", score1);

        String courseName = "Software Reuse";
        Integer year = 2022;
        Integer referenceInt = 2;
        Course.Reference reference = Course.Reference.fromInt(referenceInt);
        CourseFactory courseFactory = CourseFactory.create();
        Course course = courseFactory.createCourse(year, reference, courseName, Arrays.asList(student1));

        XStream xstream = new XStream(new StaxDriver());
        xstream.aliasType("course", Course.class);
        xstream.alias("student", Student.class);
        xstream.addImplicitCollection(FineGrainedCourse.class, "students");

        String xml = xstream.toXML(course);

        String expected = "<?xml version=\"1.0\" ?>" +
                "<course><year>2022</year>" +
                "<ref>_2</ref>" +
                "<courseName>Software Reuse</courseName>" +
                "<student><id>id1</id><score>50.0</score></student>" +
                "</course>";
//                System.out.println(xml);
        Assertions.assertEquals(expected,xml);
    }
}
