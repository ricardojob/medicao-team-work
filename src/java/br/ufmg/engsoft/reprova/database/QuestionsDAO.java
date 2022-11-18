package br.ufmg.engsoft.reprova.database;

import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.Question;
//import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;


/**
 * DAO for Question class on mongodb.
 */
public class QuestionsDAO {
  /**
   * Logger instance.
   */
  protected static final Logger logger = LoggerFactory.getLogger(QuestionsDAO.class);

  /**
   * Json formatter.
   */
//  protected  Json json;
//  protected QuestionFromDocument adapter;

//  /**
//   * Questions collection.
//   */
//  protected  MongoCollection<Document> collection;
  protected  MapperQuestionsMongo collection;

  /**
   * Basic constructor.
   * @param db    the database, mustn't be null
   * @param json  the json formatter for the database's documents, mustn't be null
   * @throws IllegalArgumentException  if any parameter is null
   */
  public QuestionsDAO(Mongo db, Json json) {
    if (db == null)
      throw new IllegalArgumentException("db mustn't be null");

    if (json == null)
      throw new IllegalArgumentException("json mustn't be null");

    this.collection = new MapperQuestionsMongo(
            db.getCollection("questions"), json
    );
//    this.adapter = new QuestionFromDocument(json);
  }


  /**
   * Get the question with the given id.
   * @param id  the question's id in the database.
   * @return The question, or null if no such question.
   * @throws IllegalArgumentException  if any parameter is null
   */
  public Question get(String id) {
    if (id == null)
      throw new IllegalArgumentException("id mustn't be null");

    Question question = this.collection.getFirst(id);
    if (question == null)
      logger.info("No such question " + id);
    return question;
  }

  /**
   * List all the questions that match the given non-null parameters.
   * The question's statement is commited.
   * @param theme      the expected theme, or null
   * @param pvt        the expected privacy, or null
   * @return The questions in the collection that match the given parameters, possibly
   *         empty.
   * @throws IllegalArgumentException  if there is an invalid Question
   */
  public Collection<Question> list(String theme, Boolean pvt) {
//    List<Bson> filters =
//      Arrays.asList(
//        theme == null ? null : eq("theme", theme),
//        pvt == null ? null : eq("pvt", pvt)
//      )
//      .stream()
//      .filter(Objects::nonNull) // mongo won't allow null filters.
//      .collect(Collectors.toList());

    Collection<Question> result = this.collection.getQuestions(theme, pvt);

    return result;
  }

  /**
   * Adds or updates the given question in the database.
   * If the given question has an id, update, otherwise add.
   * @param question  the question to be stored
   * @return Whether the question was successfully added.
   * @throws IllegalArgumentException  if any parameter is null
   */
  public boolean add(Question question) {
    if (question == null)
      throw new IllegalArgumentException("question mustn't be null");

//    List<Course> courses = question.courses;

//    Document doc = new Document()
//      .append("theme", question.theme)
//      .append("description", question.description)
//      .append("statement", question.statement)
//      .append("courses", question.courses)
//      .append("pvt", question.isPrivate);
//
//    String id = question.id;
    if (question.id != null) {
      if (extracted(question)) return false;
    }else {
      this.collection.insertOne(question);
    }
    logger.info("Stored question ");

    return true;
  }


  /**
   * Remove the question with the given id from the collection.
   * @param id  the question id
   * @return Whether the given question was removed.
   * @throws IllegalArgumentException  if any parameter is null
   */
  public boolean remove(String id) {
    if (id == null)
      throw new IllegalArgumentException("id mustn't be null");

    boolean result = this.collection.deleteOne(id);

    if (result)
      logger.info("Deleted question " + id);
    else
      logger.warn("Failed to delete question " + id);

    return result;
  }

//  private Question getFirst(String id) {
//    return this.collection
//            .find(eq(new ObjectId(id)))
//            .map(adapter::parseDoc)
//            .first();
//  }

  private boolean extracted(Question question) {
    boolean result = this.collection.replaceOne(question);
    if (!result) {
      logger.warn("Failed to replace question " + question.id);
      return true;
    }
    return false;
  }
}
