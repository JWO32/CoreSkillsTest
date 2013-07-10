package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.QuizUser;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-07-10T14:19:45")
@StaticMetamodel(UserGroup.class)
public class UserGroup_ { 

    public static volatile SingularAttribute<UserGroup, String> GroupName;
    public static volatile ListAttribute<UserGroup, QuizUser> UserList;
    public static volatile SingularAttribute<UserGroup, String> GroupDescription;
    public static volatile SingularAttribute<UserGroup, Integer> GroupID;

}