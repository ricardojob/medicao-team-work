package br.ufmg.engsoft.reprova.tests.model;

import br.ufmg.engsoft.reprova.model.CourseFactory;
import org.junit.jupiter.api.BeforeEach;

public abstract class CourseGrainedTest {
    protected CourseFactory factory;
    @BeforeEach
    public void init(){
        this.factory = getFactory();
    }
    protected abstract CourseFactory getFactory();
}
