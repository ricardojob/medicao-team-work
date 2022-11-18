package br.ufmg.engsoft.reprova.tests.database;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ufmg.engsoft.reprova.database.CoarseGrainedCourseDAO;
import br.ufmg.engsoft.reprova.model.CoarseGrainedCourse;
import br.ufmg.engsoft.reprova.model.Course;
import br.ufmg.engsoft.reprova.model.CourseFactory;
import br.ufmg.engsoft.reprova.model.variability.CoarseGrainedCourseFactory;

import static org.junit.jupiter.api.Assertions.*;

public class CoarseGrainedCourseDAOTest extends BaseTest {
	private static CoarseGrainedCourseDAO dao;

//    CourseFactory factory = new CoarseGrainedCourseFactory();
    @BeforeEach
    public  void setup() {
//		logger.info("Starting DB connection");
//		try {
			dao = new CoarseGrainedCourseDAO(db, json);
//		}
//		catch(Exception e){
//			logger.info("Could not connect to mongoDB");
//			db = null;
//		}

    }

	@Test
	void test_insertion() {

        Course course = factory.createCourse(2019, Course.Reference._1, "test_insertion", 50.0f);
        dao.add(course);
	}
	
	@Test
	void test_retrieval() {
//		CourseFactory factory = new CoarseGrainedCourseFactory();
        CoarseGrainedCourse course = (CoarseGrainedCourse)factory.createCourse(2019, Course.Reference._1, "test_retrieval", 50.0f);
        dao.add(course);
        Course course2 = dao.get(course);
        assertEquals(course.getScore(),course2.getScore());
        assertEquals(course.year,course2.year);
        assertEquals(course.ref.value,course2.ref.value);
        assertEquals(course.courseName,course2.courseName);
        
	}
	
	

}
