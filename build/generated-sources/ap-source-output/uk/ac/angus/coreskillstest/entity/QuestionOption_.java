package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Question;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-10T19:50:54")
@StaticMetamodel(QuestionOption.class)
public class QuestionOption_ { 

    public static volatile SingularAttribute<QuestionOption, Question> LinkedQuestion;
    public static volatile SingularAttribute<QuestionOption, Integer> QuestionOptionId;
    public static volatile SingularAttribute<QuestionOption, String> OptionText;
    public static volatile SingularAttribute<QuestionOption, Boolean> CorrectOption;

}