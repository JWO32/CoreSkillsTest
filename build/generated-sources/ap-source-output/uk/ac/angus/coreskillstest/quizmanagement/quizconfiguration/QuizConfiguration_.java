package uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.QuizGroup;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T21:25:02")
@StaticMetamodel(QuizConfiguration.class)
public class QuizConfiguration_ { 

    public static volatile SingularAttribute<QuizConfiguration, Integer> NumberOfQuestions;
    public static volatile SingularAttribute<QuizConfiguration, Quiz> LinkedQuiz;
    public static volatile SingularAttribute<QuizConfiguration, Calendar> QuizCloseTime;
    public static volatile SingularAttribute<QuizConfiguration, QuizGroup> LinkedGroup;
    public static volatile SingularAttribute<QuizConfiguration, Boolean> SetRandomOrder;
    public static volatile SingularAttribute<QuizConfiguration, Boolean> ReturnResult;
    public static volatile SingularAttribute<QuizConfiguration, Integer> QuizConfigId;
    public static volatile SingularAttribute<QuizConfiguration, Calendar> QuizOpenTime;

}