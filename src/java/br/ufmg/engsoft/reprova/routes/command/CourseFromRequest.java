package br.ufmg.engsoft.reprova.routes.command;

import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.ScoreFile;
import br.ufmg.engsoft.reprova.model.ScoreFileFactory;
import spark.Request;

import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CourseFromRequest {
    private final Request request;

    public CourseFromRequest(Request request) {
        this.request = request;
    }

    public Course transform() throws Exception {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        Part part = request.raw().getPart("scores_file");
        ScoreFileFactory scoreFileFactory = ScoreFileFactory.create();
        ScoreFile scoreFile = scoreFileFactory.createScoreFile(part.getSubmittedFileName());
        InputStream inputStream = part.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        Course scoredCourse = scoreFile.getScoredCourseFromFile(reader);
        reader.close();
        return scoredCourse;
    }
}
