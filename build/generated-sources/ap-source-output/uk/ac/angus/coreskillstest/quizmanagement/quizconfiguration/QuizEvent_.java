package uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.QuizGroup;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-08T13:00:12")
@StaticMetamodel(QuizEvent.class)
public class QuizEvent_ { 

    public static volatile SingularAttribute<QuizEvent, Calendar> QuizCloseDate;
    public static volatile SingularAttribute<QuizEvent, Integer> NumberOfQuestions;
    public static volatile SingularAttribute<QuizEvent, Quiz> LinkedQuiz;
    public static volatile SingularAttribute<QuizEvent, Calendar> QuizOpenDate;
    public static volatile SingularAttribute<QuizEvent, QuizGroup> LinkedGroup;
    public static volatile SingularAttribute<QuizEvent, Boolean> SetRandomOrder;
    public static volatile SingularAttribute<QuizEvent, Boolean> ReturnResult;
    public static volatile SingularAttribute<QuizEvent, Integer> QuizConfigId;

}