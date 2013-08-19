package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.QuizUser;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-18T17:07:15")
@StaticMetamodel(QuizGroup.class)
public class QuizGroup_ { 

    public static volatile SingularAttribute<QuizGroup, String> GroupName;
    public static volatile ListAttribute<QuizGroup, QuizUser> UserList;
    public static volatile ListAttribute<QuizGroup, QuizEvent> QuizConfigurationList;
    public static volatile SingularAttribute<QuizGroup, String> GroupDescription;
    public static volatile SingularAttribute<QuizGroup, Integer> GroupID;

}