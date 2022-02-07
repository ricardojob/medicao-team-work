package br.ufmg.engsoft.reprova.model;

import br.ufmg.engsoft.reprova.model.variability.CsvScoreFactory;
import br.ufmg.engsoft.reprova.model.variability.JsonScoreFactory;
import br.ufmg.engsoft.reprova.Configuration;
import br.ufmg.engsoft.reprova.model.ScoreFileFactory;
import br.ufmg.engsoft.reprova.model.variability.XMLScoreFactory;

public aspect ScoreFileTypeVariability {
    pointcut setScoreFileFactoryAsCsv(): call(ScoreFileFactory ScoreFileFactory.create(..)) && if (Configuration.isCsv());
        ScoreFileFactory around(): setScoreFileFactoryAsCsv() {
            return new CsvScoreFactory();
        }
    pointcut setScoreFileFactoryAsJson(): call(ScoreFileFactory ScoreFileFactory.create(..)) && if (Configuration.isJson());
        ScoreFileFactory around(): setScoreFileFactoryAsJson() {
                return new JsonScoreFactory();
        }
    pointcut setScoreFileFactoryAsXml(): call(ScoreFileFactory ScoreFileFactory.create(..)) && if (Configuration.isXml());
    ScoreFileFactory around(): setScoreFileFactoryAsXml() {
        return new XMLScoreFactory();
    }
}