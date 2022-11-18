package br.ufmg.engsoft.reprova.database;

import br.ufmg.engsoft.reprova.model.Question;
import br.ufmg.engsoft.reprova.routes.mapper.QuestionFromDocument;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.fields;

public class MapperIterableToList {
    protected QuestionFromDocument adapter;
    public ArrayList<Question> getQuestions(FindIterable<Document> doc) {
        ArrayList<Question> result = new ArrayList<Question>();
        doc.projection(fields(exclude("statement")))
                .map(adapter::parseDoc)
                .into(result);
        return result;
    }
}
