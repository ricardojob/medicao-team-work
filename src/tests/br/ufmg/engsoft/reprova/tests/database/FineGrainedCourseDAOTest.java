package br.ufmg.engsoft.reprova.tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import br.ufmg.engsoft.reprova.database.FineGrainedCourseDAO;
//import br.ufmg.engsoft.reprova.database.Mongo;
//import br.ufmg.engsoft.reprova.mime.json.Json;
import br.ufmg.engsoft.reprova.model.FineGrainedCourse;
import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.model.Student;
import br.ufmg.engsoft.reprova.model.variability.FineGrainedCourseFactory;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class FineGrainedCourseDAOTest extends BaseTest {
//	private static final Logger logger = LoggerFactory.getLogger(FineGrainedCourseDAOTest.class);
//	private static Mongo db;
//	private static Json json;
	private FineGrainedCourseDAO dao;
//    private CourseFactory factory = new FineGrainedCourseFactory();

	@BeforeEach
    public  void setup() {
//		logger.info("Starting DB connection");
//		try {
//			db = new Mongo("reprova");
//			json = new Json();
			dao = new FineGrainedCourseDAO(db, json);
//		}
//		catch(Exception e){
//			logger.info("Could not connect to mongoDB");
//			db = null;
//		}
		
    }
//	@BeforeEach
//	void assumeConnection() {
//		assumeFalse(db == null);
//	}
	
	@Disabled
	@Test
	void test_insertion() {
		float score1 = 50.0f;
        Student student1 = new Student("id1", score1);
        float score2 = 49.0f;
        Student student2 = new Student("id2", score2);
        Course course = factory.createCourse(2019, Course.Reference._1, "Software Reuse", Arrays.asList(student1, student2));
        dao.add(course);
	}
	
	@Disabled
	@Test
	void test_retrieval() {

        FineGrainedCourse course = (FineGrainedCourse)factory.createCourse(2019, Course.Reference._1, "test_retrieval", 50.0f);
        dao.add(course);
        Course course2 = dao.get(course);
        assertEquals(course.getScore(),course2.getScore());
        assertEquals(course.year,course2.year);
        assertEquals(course.ref.value,course2.ref.value);
        assertEquals(course.courseName,course2.courseName);
        
	}
	
	

}
