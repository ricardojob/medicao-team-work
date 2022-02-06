package br.ufmg.engsoft.reprova.model;

import java.util.ArrayList;
import java.util.List;

public class FineGrainedCourse extends Course {
    /**
     * TODO: SONIA CUIDE DISSO!
     */
    public final List<Student> students;

    public FineGrainedCourse(){
        this(2022, Reference._2, "Reuse", new ArrayList<>());
    }
    public FineGrainedCourse(int year, Reference ref, String courseName, List<Student> students) {
        super(year, ref, courseName);
        this.students = students;
    }

    @Override
    public float getScore() {
        float totalScore = 0;
        for (Student student : students) {
            totalScore += student.score;
        }
        return totalScore / this.students.size();
    }
}
