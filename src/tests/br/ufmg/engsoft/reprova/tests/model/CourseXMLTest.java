package br.ufmg.engsoft.reprova.tests.model;

import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.model.FineGrainedCourse;
import br.ufmg.engsoft.reprova.model.Student;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseXMLTest {

    private XStream xstream;

    @BeforeEach
    private void configureXStream() {
        XStream xstreamConfig = new XStream(new StaxDriver());
        xstreamConfig.aliasType("course", Course.class);
        xstreamConfig.alias("student", Student.class);
        xstreamConfig.addImplicitCollection(FineGrainedCourse.class, "students");
        this.xstream = xstreamConfig;

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

        this.xstream.alias("course", FineGrainedCourse.class);
        this.xstream.addPermission(AnyTypePermission.ANY);
        Object expected =  this.xstream.fromXML(buffer);

        String courseName = "Software Reuse";
        Integer year = 2022;
        Integer referenceInt = 2;
        Course.Reference reference = Course.Reference.fromInt(referenceInt);
        CourseFactory courseFactory = CourseFactory.create();
        Course course = courseFactory.createCourse(year, reference, courseName);

        assertEquals(expected,course);
    }
    @Test
    void testCreateWithoutStudents() {
        String courseName = "Software Reuse";
        Integer year = 2022;
        Integer referenceInt = 2;
        Course.Reference reference = Course.Reference.fromInt(referenceInt);
        CourseFactory courseFactory = CourseFactory.create();
        Course course = courseFactory.createCourse(year, reference, courseName);

        String xml = xstream.toXML(course);

        String expected = "<?xml version=\"1.0\" ?>" +
                                "<course><year>2022</year>" +
                                    "<ref>_2</ref>" +
                                    "<courseName>Software Reuse</courseName>" +
                                "</course>";

        assertEquals(expected,xml);
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
        String xml = xstream.toXML(course);

        String expected = "<?xml version=\"1.0\" ?>" +
                "<course><year>2022</year>" +
                "<ref>_2</ref>" +
                "<courseName>Software Reuse</courseName>" +
                "<student><id>id1</id><score>50.0</score></student>" +
                "</course>";
        assertEquals(expected,xml);
    }

}
