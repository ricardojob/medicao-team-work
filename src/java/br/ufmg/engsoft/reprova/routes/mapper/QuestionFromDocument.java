package br.ufmg.engsoft.reprova.routes.mapper;

import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.Question;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionFromDocument {
    protected static final Logger logger = LoggerFactory.getLogger(QuestionFromDocument.class);
    /**
     * Json formatter.
     */
  protected final Json json;

    public QuestionFromDocument(Json json) {
        this.json = json;
    }

    public Question parseDoc(Document document) {
        if (document == null)
            throw new IllegalArgumentException("document mustn't be null");

        String doc = document.toJson();

        logger.info("Fetched question: " + doc);

        try {
            Question question = json
                    .parse(doc, Question.Builder.class)
                    .build();

            logger.info("Parsed question: " + question);

            return question;
        }
        catch (Exception e) {
            logger.error("Invalid document in database!", e);
            throw new IllegalArgumentException(e);
        }
    }
}
