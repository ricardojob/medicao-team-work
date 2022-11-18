package br.ufmg.engsoft.reprova.routes.api;

import br.ufmg.engsoft.reprova.database.CourseDAO;
import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.routes.command.AddScoreCommand;
import br.ufmg.engsoft.reprova.routes.command.DeleteCourseCommand;
import br.ufmg.engsoft.reprova.routes.command.FindCourseCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class Score {
    /**
     * Logger instance.
     */
    protected static final Logger logger = LoggerFactory.getLogger(Score.class);
    /**
     * Messages.
     */
//    protected static final String invalid = "\"Invalid request\"";
//    protected static final String ok = "\"Ok\"";
    /**
     * Json formatter.
     */
    protected final Json json;

    protected final CourseDAO courseDAO;

//    private Command command;

    /**
     * Instantiate the questions endpoint.
     * The setup method must be called to install the endpoint.
     * @param json  the json formatter
     * @throws IllegalArgumentException  if any parameter is null
     */
    public Score(Json json, CourseDAO courseDAO) {
        if (json == null)
            throw new IllegalArgumentException("json mustn't be null");
        if (courseDAO == null)
            throw new IllegalArgumentException("courseDAO mustn't be null");

        this.json = json;
        this.courseDAO = courseDAO;
    }

    /**
     * Install the endpoint in Spark.
     * Methods:
     * - get
     * - post
     * - delete
     */
    public void setup() {
        Spark.get("/api/score", new FindCourseCommand(courseDAO));
        Spark.post("/api/score", new AddScoreCommand(courseDAO));
        Spark.delete("/api/score", new DeleteCourseCommand(courseDAO));
//        Spark.get("/api/score", (request, response) -> {
//            this.command = new FindCourseCommand(courseDAO);
//            return this.command.execute(request, response);
//        });
        logger.info("Setup /api/score.");
    }
    /**
     * Post endpoint: add or update a course score in the database.
     * The score file must be supplied in the request's body.
     * If the course already exist in the database, the operation is an update.
     * Otherwise, the given course is added as a new one in the database.
     */
//    protected Object post(Request request, Response response) {
//        this.command = new AddScoreCommand(courseDAO);
//        return this.command.execute(request, response);
//    }
    /**
     * Get endpoint: fetch the specified scored course from the database.
     */
//    protected Object get(Request request, Response response){
//        this.command = new FindCourseCommand(courseDAO);
//        return this.command.execute(request, response);
//    }

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

    /**
     * Delete endpoint: remove a course from the database.
     * The question's info must be provided in the request
     */
//    protected Object delete(Request request, Response response) {
//        this.command = new DeleteCourseCommand(courseDAO);
//        return this.command.execute(request, response);
//    }
}
