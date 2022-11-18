package br.ufmg.engsoft.reprova.database;

import br.ufmg.engsoft.reprova.model.Question;
import br.ufmg.engsoft.reprova.routes.mapper.QuestionFromDocument;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
//import static com.mongodb.client.model.Projections.fields;

public class MapperIterableToList {
    protected QuestionFromDocument adapter;
    public ArrayList<Question> getQuestions( MongoCollection<Document> collection, String theme, Boolean pvt) {
        List<Bson> filters = Arrays.asList(
                        theme == null ? null : eq("theme", theme),
                        pvt == null ? null : eq("pvt", pvt)
                )
                .stream()
                .filter(o-> o!=null) // mongo won't allow null filters.
                .collect(Collectors.toList());

        FindIterable<Document> doc = filters.isEmpty() // mongo won't take null as a filter.
                ? collection.find()
                : collection.find(and(filters));
        ArrayList<Question> result = new ArrayList<Question>();
        doc.projection(fields(exclude("statement")))
                .map(adapter::parseDoc)
                .into(result);
        return result;
    }
}
