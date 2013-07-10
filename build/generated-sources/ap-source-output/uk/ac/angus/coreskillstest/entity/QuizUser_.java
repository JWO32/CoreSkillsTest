package uk.ac.angus.coreskillstest.entity;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.UserGroup;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-07-10T14:19:45")
@StaticMetamodel(QuizUser.class)
public class QuizUser_ { 

    public static volatile SingularAttribute<QuizUser, Calendar> UserDateAdded;
    public static volatile SingularAttribute<QuizUser, UserGroup> UserGroup;
    public static volatile SingularAttribute<QuizUser, String> UserDescription;
    public static volatile SingularAttribute<QuizUser, String> FirstName;
    public static volatile SingularAttribute<QuizUser, Integer> UserId;
    public static volatile SingularAttribute<QuizUser, String> UserEmail;
    public static volatile SingularAttribute<QuizUser, String> LastName;

}