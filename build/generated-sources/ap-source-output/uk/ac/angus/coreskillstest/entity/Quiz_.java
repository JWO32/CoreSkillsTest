package uk.ac.angus.coreskillstest.entity;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Question;
import uk.ac.angus.coreskillstest.entity.QuizCategory;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-07-31T18:20:24")
@StaticMetamodel(Quiz.class)
public class Quiz_ { 

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