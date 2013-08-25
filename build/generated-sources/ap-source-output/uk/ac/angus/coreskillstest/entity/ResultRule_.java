package uk.ac.angus.coreskillstest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.ac.angus.coreskillstest.entity.Quiz;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-08-25T23:28:30")
@StaticMetamodel(ResultRule.class)
public class ResultRule_ { 

    public static volatile SingularAttribute<ResultRule, Integer> LowMarkBoundary;
    public static volatile SingularAttribute<ResultRule, Boolean> LowMarkGtEq;
    public static volatile SingularAttribute<ResultRule, Boolean> HighMarkGtEq;
    public static volatile SingularAttribute<ResultRule, Quiz> LinkedQuiz;
    public static volatile SingularAttribute<ResultRule, Integer> ResultRuleId;
    public static volatile SingularAttribute<ResultRule, Integer> HighMarkBoundary;
    public static volatile SingularAttribute<ResultRule, Boolean> PassFail;
    public static volatile SingularAttribute<ResultRule, String> RuleName;

}