package controllers;

import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import repository.Dao;

import javax.inject.Inject;

public class SQLExecutionController extends Controller {
    private static final Logger.ALogger logger = Logger.of(SQLExecutionController.class);

    private Dao dao;

    @Inject
    public SQLExecutionController(Dao dao) {
        this.dao = dao;
    }

    @Transactional
    public Result update() {
        try {
            return ok(dao.executeUpdate(request().body().asText()));
        } catch (Exception e) {
            logger.info("Exception executing query - " + e);
            return internalServerError("ERROR");
        }


    }

    @Transactional
    public Result query() {
        try {
            return ok(dao.executeQuery(request().body().asText()));
        } catch (Exception e) {
            logger.info("Exception executing query - " + e);
            return internalServerError(e.getMessage());
        }

    }

}
