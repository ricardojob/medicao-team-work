package br.ufmg.engsoft.reprova.routes.command;

import br.ufmg.engsoft.reprova.database.CourseDAO;
import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.ScoreFile;
import br.ufmg.engsoft.reprova.model.ScoreFileFactory;
import br.ufmg.engsoft.reprova.model.Student;
import br.ufmg.engsoft.reprova.routes.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddScoreCommand extends Command {
    /**
     * Logger instance.
     */
    protected static final Logger logger = LoggerFactory.getLogger(AddScoreCommand.class);
    /**
     * Messages.
     */
    protected static final String invalid = "\"Invalid request\"";
    protected static final String ok = "\"Ok\"";
    private final CourseDAO courseDAO;

    public AddScoreCommand(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public Object execute(Request request, Response response)  {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        List<Student> students = new ArrayList<Student>();
        Course course= null;

        try {
            Part part =request.raw().getPart("scores_file");
            ScoreFileFactory scoreFileFactory = ScoreFileFactory.create();
            ScoreFile scoreFile = scoreFileFactory.createScoreFile(part.getSubmittedFileName());
            InputStream inputStream = part.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Course scoredCourse = scoreFile.getScoredCourseFromFile(reader);
            reader.close();
            courseDAO.add(scoredCourse);

        }
        catch (Exception e){
            logger.error("Invalid request payload!", e);
            response.status(400);
            return invalid;
        }
        return ok;
    }


}
