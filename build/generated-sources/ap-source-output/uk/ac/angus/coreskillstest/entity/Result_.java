package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Feedback;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.QuizUser;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-14T23:38:56")
@StaticMetamodel(Result.class)
public class Result_ { 

    public static volatile SingularAttribute<Result, QuizUser> LinkedUser;
    public static volatile SingularAttribute<Result, Integer> QuizScore;
    public static volatile SingularAttribute<Result, Integer> ResultId;
    public static volatile SingularAttribute<Result, Quiz> LinkedQuiz;
    public static volatile SingularAttribute<Result, Float> QuizPercentage;
    public static volatile SingularAttribute<Result, Feedback> LinkedFeedback;
    public static volatile SingularAttribute<Result, String> ResultStatus;

}