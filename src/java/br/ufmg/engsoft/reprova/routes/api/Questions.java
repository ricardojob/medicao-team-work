package br.ufmg.engsoft.reprova.routes.api;

import br.ufmg.engsoft.reprova.routes.Command;
import br.ufmg.engsoft.reprova.routes.command.CreateOrUpdateQuestionCommand;
import br.ufmg.engsoft.reprova.routes.command.DeleteQuestionCommand;
import br.ufmg.engsoft.reprova.routes.command.FindQuestionsCommand;
import spark.Spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufmg.engsoft.reprova.database.QuestionsDAO;
import br.ufmg.engsoft.reprova.mime.json.Json;

import java.util.Collection;


/**
 * Questions route.
 */
public class Questions {
  /**
   * Logger instance.
   */
  protected static final Logger logger = LoggerFactory.getLogger(Questions.class);

  /**
   * Access token.
   */
//  protected static final String token = System.getenv("REPROVA_TOKEN");

  /**
   * Messages.
   */
//  protected static final String unauthorised = "\"Unauthorised\"";
//  protected static final String invalid = "\"Invalid request\"";
//  protected static final String ok = "\"Ok\"";


  /**
   * Json formatter.
   */
  protected final Json json;
  /**
   * DAO for Question.
   */
  protected final QuestionsDAO questionsDAO;
  private Command command;


  /**
   * Instantiate the questions endpoint.
   * The setup method must be called to install the endpoint.
   * @param json          the json formatter
   * @param questionsDAO  the DAO for Question
   * @throws IllegalArgumentException  if any parameter is null
   */
  public Questions(Json json, QuestionsDAO questionsDAO) {
    if (json == null)
      throw new IllegalArgumentException("json mustn't be null");

    if (questionsDAO == null)
      throw new IllegalArgumentException("questionsDAO mustn't be null");

    this.json = json;
    this.questionsDAO = questionsDAO;
  }

  /**
   * Install the endpoint in Spark.
   * Methods:
   * - get
   * - post
   * - delete
   */
  public void setup() {
    Spark.get("/api/questions", new FindQuestionsCommand(questionsDAO,json));
    Spark.post("/api/questions", new CreateOrUpdateQuestionCommand(questionsDAO,json));
    Spark.delete("/api/questions", new DeleteQuestionCommand(questionsDAO));
    logger.info("Setup /api/questions.");
  }


  /**
   * Check if the given token is authorised.
   */
//  protected static boolean authorised(String token) {
//    return Questions.token.equals(token);
//  }


  /**
   * Get endpoint: lists all questions, or a single question if a 'id' query parameter is
   * provided.
   */
//  protected Object get(Request request, Response response) {
//    this.command = new FindQuestionsCommand(questionsDAO, json);
//    return this.command.execute(request, response);
////    logger.info("Received questions get:"); //logger, tracking
////
////    String id = request.queryParams("id");
////    boolean auth = authorised(request.queryParams("token")); //point aspectj
////    //user:pass:type ->  hash
////    return id == null
////      ? this.get(request, response, auth)
////      : this.get(request, response, id, auth);
//  }

  /**
   * Get id endpoint: fetch the specified question from the database.
   * If not authorised, and the given question is private, returns an error message.
   */
//  protected Object get(Request request, Response response, String id, boolean auth) {
//    if (id == null)
//      throw new IllegalArgumentException("id mustn't be null");
//
//    response.type("application/json");
//
//    logger.info("Fetching question " + id);
//
//    Question question = questionsDAO.get(id);
//
//    if (question == null) {
//      logger.error("Invalid request!");
//      response.status(400);
//      return invalid;
//    }
//
//    if (question.isPrivate && !auth) {
//      logger.info("Unauthorised token: " + token);
//      response.status(403);
//      return unauthorised;
//    }
//
//    logger.info("Done. Responding...");
//
//    response.status(200);
//
//    return json.render(question);
//  }
//
//  /**
//   * Get all endpoint: fetch all questions from the database.
//   * If not authorised, fetches only public questions.
//   */
//  protected Object get(Request request, Response response, boolean auth) {
//    response.type("application/json");
//
//    logger.info("Fetching questions.");
//
//    Collection<Question> questions = questionsDAO.list(
//      null, // theme filtering is not implemented in this endpoint.
//      auth ? null : false
//    );
//
//    logger.info("Done. Responding...");
//
//    response.status(200);
//
//    return json.render(questions);
//  }


  /**
   * Post endpoint: add or update a question in the database.
   * The question must be supplied in the request's body.
   * If the question has an 'id' field, the operation is an update.
   * Otherwise, the given question is added as a new question in the database.
   * This endpoint is for authorized access only.
   */
//  protected Object post(Request request, Response response) {
//    this.command = new CreateOrUpdateQuestionCommand(questionsDAO, json);
//    return this.command.execute(request, response);
////    String body = request.body();
////
////    logger.info("Received questions post:" + body);
////
////    response.type("application/json");
////
////    String token = request.queryParams("token");
////
////    if (!authorised(token)) {
////      logger.info("Unauthorised token: " + token);
////      response.status(403);
////      return unauthorised;
////    }
////
////    Question question;
////    try {
////      question = json
////        .parse(body, Question.Builder.class)
////        .build();
////    }
////    catch (Exception e) {
////      logger.error("Invalid request payload!", e);
////      response.status(400);
////      return invalid;
////    }
////
////    logger.info("Parsed " + question.toString());
////
////    logger.info("Adding question.");
////
////    boolean success = questionsDAO.add(question);
////
////    response.status(
////       success ? 200
////               : 400
////    );
////
////    logger.info("Done. Responding...");
////
////    return ok;
//  }


  /**
   * Delete endpoint: remove a question from the database.
   * The question's id must be supplied through the 'id' query parameter.
   * This endpoint is for authorized access only.
   */
//  protected Object delete(Request request, Response response) {
//    this.command = new DeleteQuestionCommand(questionsDAO);
//    return this.command.execute(request, response);
//  }
}
