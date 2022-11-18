package br.ufmg.engsoft.reprova.routes.mapper;

import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.ScoreFile;
import br.ufmg.engsoft.reprova.model.ScoreFileFactory;

import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;

public class CourseFromReader {
    public Course getCourse(Part part, BufferedReader reader) throws IOException {
        ScoreFileFactory scoreFileFactory = ScoreFileFactory.create();
        ScoreFile scoreFile = scoreFileFactory.createScoreFile(part.getSubmittedFileName());
        Course scoredCourse = scoreFile.getScoredCourseFromFile(reader);
        return scoredCourse;
    }
}
