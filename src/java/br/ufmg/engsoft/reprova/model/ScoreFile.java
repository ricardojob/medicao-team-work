package br.ufmg.engsoft.reprova.model;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class ScoreFile {
    private String format;

    public ScoreFile(String format) {
        this.format = format;
    }

    public abstract Course getScoredCourseFromFile(BufferedReader reader) throws IOException;
    public  boolean validateFile(String submittedFileName){
        return submittedFileName.endsWith(this.format);
    }
    public String getFormat(){
        return this.format;
    }
}
