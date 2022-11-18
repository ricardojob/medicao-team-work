package br.ufmg.engsoft.reprova.tests.database;

import br.ufmg.engsoft.reprova.database.Mongo;
import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.model.variability.CoarseGrainedCourseFactory;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assumptions.assumeFalse;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(CoarseGrainedCourseDAOTest.class);
    protected static Mongo db;
    protected static Json json;
    protected CourseFactory factory = new CoarseGrainedCourseFactory();

    public BaseTest(){
        logger.info("Starting DB connection");
        try {
            db = new Mongo("reprova");
            json = new Json();
        }
        catch(Exception e){
            logger.info("Could not connect to mongoDB");
            db = null;
        }
    }

    @BeforeEach
    void assumeConnection() {
        assumeFalse(db == null);
    }
}
