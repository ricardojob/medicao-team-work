package br.ufmg.engsoft.reprova.tests.mime.json;

import br.ufmg.engsoft.reprova.Configuration;
import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonSimpleTest {

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
