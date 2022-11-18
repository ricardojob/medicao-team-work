package br.ufmg.engsoft.reprova.routes;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class Command implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return execute(request, response);
    }
    public abstract Object execute(Request request, Response response);
}
