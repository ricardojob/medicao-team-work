package br.ufmg.engsoft.reprova.routes.command;

import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.model.ScoreFile;
import br.ufmg.engsoft.reprova.model.ScoreFileFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CourseJsonRequest {
    private final Request request;

    public CourseJsonRequest(Request request) {
        this.request = request;
    }

    public Course transform(){
        String body = request.body();
        JsonParser jsonParser = new JsonParser();
        Object object = jsonParser.parse(body);
        JsonObject jsonBody = (JsonObject) object;
        Integer year = jsonBody.get("year").getAsInt();
        Integer referenceInteger = jsonBody.get("ref").getAsInt();
        Course.Reference reference = Course.Reference.fromInt(referenceInteger);
        String courseName = jsonBody.get("courseName").toString();
        Course course = CourseFactory.create().createCourse(year,reference,courseName);
        return course;
    }
}
