package br.ufmg.engsoft.reprova.routes;

import br.ufmg.engsoft.reprova.model.Course;
import spark.Request;

public interface CourseAdapter {
    Course transform(Request request);
}
