package br.ufmg.engsoft.reprova.routes.command;

import br.ufmg.engsoft.reprova.database.CourseDAO;
import br.ufmg.engsoft.reprova.model.Course;
//import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.routes.Command;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

public class FindCourseCommand extends Command {
    private final CourseDAO courseDAO;

    public FindCourseCommand(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public Object execute(Request request, Response response) {
        CourseJsonRequest adapter = new CourseJsonRequest(request);
        Course course = adapter.transform();
        course = courseDAO.get(course);
        return course;
    }
//    private Course getCourseFromRequest(Request request) {
//        String body = request.body();
//        JsonParser jsonParser = new JsonParser();
//        Object object = jsonParser.parse(body);
//        JsonObject jsonBody = (JsonObject) object;
//        Integer year = jsonBody.get("year").getAsInt();
//        Integer referenceInteger = jsonBody.get("ref").getAsInt();
//        Course.Reference reference = Course.Reference.fromInt(referenceInteger);
//        String courseName = jsonBody.get("courseName").toString();
//        Course course = CourseFactory.create().createCourse(year,reference,courseName);
//        return course;
//    }
}
