package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.QuizCategory;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-18T15:11:10")
@StaticMetamodel(ResultFeedback.class)
public class ResultFeedback_ { 

    public static volatile SingularAttribute<ResultFeedback, QuizCategory> LinkedResult;
    public static volatile SingularAttribute<ResultFeedback, Integer> ResultFeedbackId;
    public static volatile SingularAttribute<ResultFeedback, Float> PercentageScore;
    public static volatile SingularAttribute<ResultFeedback, String> FeedbackText;
    public static volatile SingularAttribute<ResultFeedback, Boolean> PassOrFail;

}