package br.ufmg.engsoft.reprova.routes.command;

import br.ufmg.engsoft.reprova.database.QuestionsDAO;
import br.ufmg.engsoft.reprova.routes.Command;
import br.ufmg.engsoft.reprova.routes.api.Questions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class DeleteQuestionCommand extends Command {

    protected static final Logger logger = LoggerFactory.getLogger(Questions.class);
    protected static final String token = System.getenv("REPROVA_TOKEN");
    protected static final String unauthorised = "\"Unauthorised\"";
    protected static final String invalid = "\"Invalid request\"";
    protected static final String ok = "\"Ok\"";

    private final QuestionsDAO questionsDAO;

    public DeleteQuestionCommand(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    @Override
    public Object execute(Request request, Response response) {
        logger.info("Received questions delete:");

        response.type("application/json");

        String id = request.queryParams("id");
        String token = request.queryParams("token");

        if (!authorised(token)) {
            logger.info("Unauthorised token: " + token);
            response.status(403);
            return unauthorised;
        }

        if (id == null) {
            logger.error("Invalid request!");
            response.status(400);
            return invalid;
        }

        logger.info("Deleting question " + id);

        boolean success = questionsDAO.remove(id);

        logger.info("Done. Responding...");

        response.status(
                success ? 200
                        : 400
        );

        return ok;
    }
    protected static boolean authorised(String token) {
        return DeleteQuestionCommand.token.equals(token);
    }
}
