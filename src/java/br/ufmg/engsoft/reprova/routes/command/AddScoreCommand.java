package br.ufmg.engsoft.reprova.routes.command;

import br.ufmg.engsoft.reprova.database.CourseDAO;
import br.ufmg.engsoft.reprova.model.Course;
//import br.ufmg.engsoft.reprova.model.ScoreFile;
//import br.ufmg.engsoft.reprova.model.ScoreFileFactory;
import br.ufmg.engsoft.reprova.routes.Command;
import br.ufmg.engsoft.reprova.routes.CourseAdapter;
import br.ufmg.engsoft.reprova.routes.mapper.CourseFromRequest;
import spark.Request;
import spark.Response;

//import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletException;
//import javax.servlet.http.Part;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;

public class AddScoreCommand extends Command {
    /**
     * Logger instance.
     */
//    protected static final Logger logger = LoggerFactory.getLogger();
    /**
     * Messages.
     */
//    protected static final String invalid = "\"Invalid request\"";
//    protected static final String ok = "\"Ok\"";
    private final CourseDAO courseDAO;
    private CourseAdapter adapter = new CourseFromRequest();
    public AddScoreCommand(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public Object execute(Request request, Response response)  {
        try {
//            CourseFromRequest adapter = new CourseFromRequest();
            Course scoredCourse = adapter.transform(request);
            courseDAO.add(scoredCourse);
        }
        catch (Exception e){
            logger.error("Invalid request payload!", e);
            response.status(400);
            return invalid;
        }
        return ok;
    }

//    private Course getCourse(Request request) throws IOException, ServletException {
//        Part part = request.raw().getPart("scores_file");
//        ScoreFileFactory scoreFileFactory = ScoreFileFactory.create();
//        ScoreFile scoreFile = scoreFileFactory.createScoreFile(part.getSubmittedFileName());
//        InputStream inputStream = part.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//        Course scoredCourse = scoreFile.getScoredCourseFromFile(reader);
//        reader.close();
//        return scoredCourse;
//    }



}
