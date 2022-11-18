package br.ufmg.engsoft.reprova.routes.mapper;

import br.ufmg.engsoft.reprova.model.Course;
//import br.ufmg.engsoft.reprova.model.ScoreFile;
//import br.ufmg.engsoft.reprova.model.ScoreFileFactory;
import br.ufmg.engsoft.reprova.routes.CourseAdapter;
import spark.Request;

import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CourseFromRequest implements CourseAdapter {
    @Override
    public Course transform(Request request) throws RuntimeException {
        try {
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            Part part = request.raw().getPart("scores_file");
            InputStream inputStream = part.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Course scoredCourse = new CourseFromReader().getCourse(part, reader);
            reader.close();
            return scoredCourse;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
