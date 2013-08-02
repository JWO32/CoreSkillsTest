package uk.ac.angus.coreskillstest.entity;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.QuizGroup;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-02T16:26:48")
@StaticMetamodel(QuizUser.class)
public class QuizUser_ { 

    public static volatile SingularAttribute<QuizUser, String> Description;
    public static volatile SingularAttribute<QuizUser, String> Email;
    public static volatile SingularAttribute<QuizUser, Calendar> DateAdded;
    public static volatile SingularAttribute<QuizUser, String> FirstName;
    public static volatile SingularAttribute<QuizUser, Integer> UserId;
    public static volatile SingularAttribute<QuizUser, String> LastName;
    public static volatile SingularAttribute<QuizUser, QuizGroup> Group;

}