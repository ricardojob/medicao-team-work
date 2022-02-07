package br.ufmg.engsoft.reprova.model.variability;

import br.ufmg.engsoft.reprova.model.ScoreCsv;
import br.ufmg.engsoft.reprova.model.ScoreFile;
import br.ufmg.engsoft.reprova.model.ScoreFileFactory;
import br.ufmg.engsoft.reprova.model.ScoreXml;

public class XMLScoreFactory extends ScoreFileFactory {
    @Override
    public ScoreFile createScoreFile(String submittedFileName) {
        ScoreXml scoreFile = new ScoreXml();
        if (!scoreFile.validateFile(submittedFileName)){
            throw new IllegalArgumentException("Score file must be .xml");
        }
        return scoreFile;
    }
}
