package br.ufmg.engsoft.reprova.tests.mime.json;

import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.CoarseGrainedCourse;
import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.Question;

public abstract class JsonAbstractTest {

    protected Question parser(Question question) {
        Json formatter = new Json();

        String json = formatter.render(question);

        Question questionCopy = formatter
                .parse(json, Question.Builder.class)
                .build();
        return questionCopy;
    }
    protected Course parse(Course course, Class<? extends Course> clazz){
        Json formatter = new Json();
        String json = formatter.render(course);
        Course courseCopy = formatter
                .parse(json, clazz);
        return courseCopy;
    }

}
