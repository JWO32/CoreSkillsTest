package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Result;
import uk.ac.angus.coreskillstest.entity.ResultRule;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-14T23:38:56")
@StaticMetamodel(Feedback.class)
public class Feedback_ { 

    public static volatile SingularAttribute<Feedback, ResultRule> LinkedResultRule;
    public static volatile SingularAttribute<Feedback, String> FeedbackText;
    public static volatile ListAttribute<Feedback, Result> LinkedResults;

}