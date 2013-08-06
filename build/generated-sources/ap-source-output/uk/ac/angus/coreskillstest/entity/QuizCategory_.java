package uk.ac.angus.coreskillstest.entity;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Quiz;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-05T21:25:02")
@StaticMetamodel(QuizCategory.class)
public class QuizCategory_ { 

    public static volatile SingularAttribute<QuizCategory, ArrayList> Category;
    public static volatile SingularAttribute<QuizCategory, Quiz> LinkedQuiz;
    public static volatile SingularAttribute<QuizCategory, Integer> QuizCategoryId;

}