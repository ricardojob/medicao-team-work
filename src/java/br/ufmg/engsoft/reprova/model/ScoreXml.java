package br.ufmg.engsoft.reprova.model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreXml extends ScoreFile{
    public ScoreXml() {
        super(".xml");
    }
    @Override
    public Course getScoredCourseFromFile(BufferedReader reader) throws IOException {
        try{
            XStream xstream = new XStream(new StaxDriver());
            xstream.alias("student", Student.class);
            xstream.alias("course", FineGrainedCourse.class);
            xstream.addImplicitCollection(FineGrainedCourse.class, "students");
            xstream.addPermission(AnyTypePermission.ANY);
            Object expected =  xstream.fromXML(reader);
            return (Course) expected;
        }catch (Exception e){
            throw new IOException("Error reading file");
        }
    }
}
