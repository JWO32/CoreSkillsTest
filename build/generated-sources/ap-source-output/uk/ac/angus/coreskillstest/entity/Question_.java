package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.QuestionCategory;
import uk.ac.angus.coreskillstest.entity.QuestionOption;
import uk.ac.angus.coreskillstest.entity.Quiz;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-15T17:07:24")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, String> QuestionText;
    public static volatile SingularAttribute<Question, Integer> QuestionScore;
    public static volatile ListAttribute<Question, QuestionOption> QuestionOptions;
    public static volatile SingularAttribute<Question, Quiz> LinkedQuiz;
    public static volatile SingularAttribute<Question, Integer> QuestionId;
    public static volatile SingularAttribute<Question, String> QuestionLevel;
    public static volatile SingularAttribute<Question, QuestionCategory> QCategory;

}