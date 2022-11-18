package br.ufmg.engsoft.reprova.database;

import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.Question;
import br.ufmg.engsoft.reprova.routes.mapper.QuestionFromDocument;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MapperQuestionsMongo {
    /**
     * Questions collection.
     */
    protected MongoCollection<Document> collection;
    protected QuestionFromDocument adapter;

    public MapperQuestionsMongo(MongoCollection<Document> questions, Json json) {
        this.collection = questions;
        this.adapter = new QuestionFromDocument(json);
    }
    public List<Question> getQuestions(String theme, Boolean pvt) {
//        List<Bson> filters =Arrays.asList(
//                                theme == null ? null : eq("theme", theme),
//                                pvt == null ? null : eq("pvt", pvt)
//                        )
//                        .stream()
//                        .filter(o-> o!=null) // mongo won't allow null filters.
//                        .collect(Collectors.toList());
//
//        FindIterable<Document> doc = filters.isEmpty() // mongo won't take null as a filter.
//                ? this.collection.find()
//                : this.collection.find(and(filters));
        List<Question> result = new MapperIterableToList().getQuestions(this.collection, theme, pvt);
        return result;
    }

    public void insertOne(Question question) {
        Document doc = createDocument(question);
        this.collection.insertOne(doc);
    }



    public boolean deleteOne(String id) {
        boolean result = this.collection.deleteOne(
                eq(new ObjectId(id))
        ).wasAcknowledged();
        return result;
    }

    public Question getFirst(String id) {
        return this.collection
                .find(eq(new ObjectId(id)))
                .map(adapter::parseDoc)
                .first();
    }

    public boolean replaceOne(Question question) {
        Document doc = createDocument(question);

        UpdateResult result = this.collection.replaceOne(
                eq(new ObjectId(question.id)),
                doc
        );
        return result.wasAcknowledged();
    }
    private Document createDocument(Question question) {
        Document doc = new Document()
                .append("theme", question.theme)
                .append("description", question.description)
                .append("statement", question.statement)
                .append("courses", question.courses)
                .append("pvt", question.isPrivate);
        return doc;
    }
}
