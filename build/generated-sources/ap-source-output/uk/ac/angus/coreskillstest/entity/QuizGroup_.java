package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.QuizUser;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-02T16:26:48")
@StaticMetamodel(QuizGroup.class)
public class QuizGroup_ { 

    public static volatile SingularAttribute<QuizGroup, String> GroupName;
    public static volatile ListAttribute<QuizGroup, QuizUser> UserList;
    public static volatile SingularAttribute<QuizGroup, String> GroupDescription;
    public static volatile SingularAttribute<QuizGroup, Integer> GroupID;

}