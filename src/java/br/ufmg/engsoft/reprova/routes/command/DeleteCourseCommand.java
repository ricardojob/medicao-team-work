package br.ufmg.engsoft.reprova.routes.command;

import br.ufmg.engsoft.reprova.database.CourseDAO;
import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.routes.Command;
import br.ufmg.engsoft.reprova.routes.CourseAdapter;
import br.ufmg.engsoft.reprova.routes.mapper.CourseJsonRequest;
import spark.Request;
import spark.Response;

public class DeleteCourseCommand extends Command {
    private final CourseDAO courseDAO;
//    protected static final String ok = "\"Ok\"";
private CourseAdapter adapter = new CourseJsonRequest();
    public DeleteCourseCommand(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public Object execute(Request request, Response response) {
        Course course = adapter.transform(request);
        courseDAO.delete(course);
        return ok;
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
