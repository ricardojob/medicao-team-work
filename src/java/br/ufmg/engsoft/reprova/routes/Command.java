package br.ufmg.engsoft.reprova.routes;

import br.ufmg.engsoft.reprova.routes.command.CreateOrUpdateQuestionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class Command implements Route {

    protected static final Logger logger = LoggerFactory.getLogger(Command.class);
    protected final String token = System.getenv("REPROVA_TOKEN");
    protected static final String unauthorised = "\"Unauthorised\"";
    protected static final String invalid = "\"Invalid request\"";
    protected static final String ok = "\"Ok\"";


    @Override
    public Object handle(Request request, Response response) throws Exception {
        return execute(request, response);
    }
    public abstract Object execute(Request request, Response response);

    protected boolean authorised(String token) {
        return token.equals(token);
    }
}
