package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Quiz;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-15T12:51:50")
@StaticMetamodel(QuizMessage.class)
public class QuizMessage_ { 

    public static volatile SingularAttribute<QuizMessage, Integer> MessageId;
    public static volatile SingularAttribute<QuizMessage, Integer> PassMark;
    public static volatile SingularAttribute<QuizMessage, Quiz> LinkedQuiz;
    public static volatile SingularAttribute<QuizMessage, String> Content;
    public static volatile SingularAttribute<QuizMessage, String> Title;
    public static volatile SingularAttribute<QuizMessage, Boolean> IsStartMessage;

}