package br.ufmg.engsoft.reprova.routes.command;

import br.ufmg.engsoft.reprova.database.QuestionsDAO;
import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.Question;
import br.ufmg.engsoft.reprova.routes.Command;
import spark.Request;
import spark.Response;

import java.util.Collection;

public class FindQuestionsCommand extends Command {
//    protected static final Logger logger = LoggerFactory.getLogger(FindQuestionsCommand.class);
//    protected static final String token = System.getenv("REPROVA_TOKEN");
//    protected static final String unauthorised = "\"Unauthorised\"";
//    protected static final String invalid = "\"Invalid request\"";
//    protected static final String ok = "\"Ok\"";
    private final QuestionsDAO questionsDAO;
    private final Json json;

    public FindQuestionsCommand(QuestionsDAO questionsDAO, Json json) {
        this.questionsDAO = questionsDAO;
        this.json = json;
    }

    @Override
    public Object execute(Request request, Response response) {
        logger.info("Received questions get:"); //logger, tracking

        String id = request.queryParams("id");
        boolean auth = authorised(request.queryParams("token")); //point aspectj
        //user:pass:type ->  hash
        return id == null
                ? this.get(request, response, auth)
                : this.get(request, response, id, auth);
    }
//      protected static boolean authorised(String token) {
//     return FindQuestionsCommand.token.equals(token);
//  }
    protected Object get(Request request, Response response, String id, boolean auth) {
        if (id == null)
            throw new IllegalArgumentException("id mustn't be null");

        response.type("application/json");

        logger.info("Fetching question " + id);

        Question question = questionsDAO.get(id);

        if (question == null) {
            logger.error("Invalid request!");
            response.status(400);
            return invalid;
        }

        if (question.isPrivate && !auth) {
            logger.info("Unauthorised token: " + token);
            response.status(403);
            return unauthorised;
        }

        logger.info("Done. Responding...");

        response.status(200);

        return json.render(question);
    }

    /**
     * Get all endpoint: fetch all questions from the database.
     * If not authorised, fetches only public questions.
     */
    protected Object get(Request request, Response response, boolean auth) {
        response.type("application/json");

        logger.info("Fetching questions.");

        Collection<Question> questions = questionsDAO.list(
                null, // theme filtering is not implemented in this endpoint.
                auth ? null : false
        );

        logger.info("Done. Responding...");

        response.status(200);

        return json.render(questions);
    }
}
