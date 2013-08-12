package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Question;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-12T15:40:53")
@StaticMetamodel(QuestionCategory.class)
public class QuestionCategory_ { 

    public static volatile ListAttribute<QuestionCategory, Question> LinkedQuestion;
    public static volatile SingularAttribute<QuestionCategory, Integer> QuestionCategoryId;
    public static volatile SingularAttribute<QuestionCategory, String> CategoryDescription;

}