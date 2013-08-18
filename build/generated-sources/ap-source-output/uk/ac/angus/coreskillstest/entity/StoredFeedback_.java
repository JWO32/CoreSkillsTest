package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.ResultRule;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-18T15:11:10")
@StaticMetamodel(StoredFeedback.class)
public class StoredFeedback_ { 

    public static volatile SingularAttribute<StoredFeedback, Integer> FeedbackId;
    public static volatile SingularAttribute<StoredFeedback, ResultRule> LinkedResultRule;
    public static volatile SingularAttribute<StoredFeedback, String> FeedbackText;

}