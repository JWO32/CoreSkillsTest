package uk.ac.angus.coreskillstest.entity;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Question;
import uk.ac.angus.coreskillstest.entity.QuizCategory;
import uk.ac.angus.coreskillstest.entity.QuizMessage;
import uk.ac.angus.coreskillstest.entity.ResultRule;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-20T11:33:33")
@StaticMetamodel(Quiz.class)
public class Quiz_ { 

    public static volatile ListAttribute<Quiz, QuizMessage> QuizMessages;
    public static volatile ListAttribute<Quiz, ResultRule> ResultRules;
    public static volatile ListAttribute<Quiz, QuizEvent> QuizEvents;
    public static volatile SingularAttribute<Quiz, Integer> TotalMarks;
    public static volatile ListAttribute<Quiz, Question> Questions;
    public static volatile SingularAttribute<Quiz, Integer> QuizId;
    public static volatile SingularAttribute<Quiz, Calendar> DateAdded;
    public static volatile SingularAttribute<Quiz, String> QuizTitle;
    public static volatile SingularAttribute<Quiz, String> QuizSubject;
    public static volatile SingularAttribute<Quiz, String> QuizLevel;
    public static volatile SingularAttribute<Quiz, Integer> QuizDuration;
    public static volatile SingularAttribute<Quiz, QuizCategory> QCategory;

}