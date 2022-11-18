package br.ufmg.engsoft.reprova.routes.command;

import br.ufmg.engsoft.reprova.database.QuestionsDAO;
import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.Question;
import br.ufmg.engsoft.reprova.routes.Command;
import br.ufmg.engsoft.reprova.routes.api.Questions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class CreateOrUpdateQuestionCommand extends Command {
    protected static final Logger logger = LoggerFactory.getLogger(Questions.class);
    protected static final String token = System.getenv("REPROVA_TOKEN");
    protected static final String unauthorised = "\"Unauthorised\"";
    protected static final String invalid = "\"Invalid request\"";
    protected static final String ok = "\"Ok\"";

    private final QuestionsDAO questionsDAO;
    protected final Json json;

    public CreateOrUpdateQuestionCommand(QuestionsDAO questionsDAO, Json json) {
        this.questionsDAO = questionsDAO;
        this.json = json;
    }

    @Override
    public Object execute(Request request, Response response) {
        String body = request.body();
        logger.info("Received questions post:" + body);
        response.type("application/json");
        String token = request.queryParams("token");

        if (!authorised(token)) {
            logger.info("Unauthorised token: " + token);
            response.status(403);
            return unauthorised;
        }

        Question question;
        try {
            question = this.json
                    .parse(body, Question.Builder.class)
                    .build();
        }
        catch (Exception e) {
            logger.error("Invalid request payload!", e);
            response.status(400);
            return invalid;
        }

        logger.info("Parsed " + question.toString());

        logger.info("Adding question.");

        boolean success = questionsDAO.add(question);

        response.status(
                success ? 200
                        : 400
        );
        logger.info("Done. Responding...");
        return ok;
    }
    protected static boolean authorised(String token) {
        return CreateOrUpdateQuestionCommand.token.equals(token);
    }
}
