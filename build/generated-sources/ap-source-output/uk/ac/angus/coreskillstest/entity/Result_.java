package uk.ac.angus.coreskillstest.entity;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.QuizUser;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-19T22:02:11")
@StaticMetamodel(Result.class)
public class Result_ { 

    public static volatile SingularAttribute<Result, QuizUser> LinkedUser;
    public static volatile SingularAttribute<Result, Integer> QuizScore;
    public static volatile SingularAttribute<Result, Integer> ResultId;
    public static volatile SingularAttribute<Result, Quiz> LinkedQuiz;
    public static volatile SingularAttribute<Result, Float> QuizPercentage;
    public static volatile SingularAttribute<Result, String> LinkedFeedback;
    public static volatile SingularAttribute<Result, Calendar> ResultDate;
    public static volatile SingularAttribute<Result, String> ResultStatus;

}