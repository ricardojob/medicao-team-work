package br.ufmg.engsoft.reprova.routes.mapper;

import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.routes.CourseAdapter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.Request;

public class CourseJsonRequest implements CourseAdapter {

    public Course transform(Request request){
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
