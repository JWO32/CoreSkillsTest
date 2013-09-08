package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;

/**
 * Class QuizGroup
 * 
 * Manages a collection of users that form a class or group in the quiz system
 * @author JWO
 * 
 * REMEMBER: Use SELECT DISTINCT in join queries to avoid return of duplicate data
 * 
 * Removed XML annotations - moving to json for data transfer, means less server
 * load when dealing with large numbers of connections.
 * 
 * @Expose annotation required for fields that do NOT link to external objects
 * (to avoid infinite recursion when serialising to JSON string).
 */

@Entity(name="QUIZ_GROUP")
@NamedQueries({
    @NamedQuery(name="Groups.getAllGroupsAndUsers", 
        query="SELECT DISTINCT g from QUIZ_GROUP g LEFT JOIN FETCH g.UserList"),
    
    @NamedQuery(name="Groups.getAllGroups", 
        query="SELECT g from QUIZ_GROUP g"),

    @NamedQuery(name="Groups.findGroupById",
        query="SELECT g from QUIZ_GROUP g WHERE g.GroupID = :id"),
    
    @NamedQuery(name="Groups.deleteGroupById",
        query="DELETE FROM QUIZ_GROUP g WHERE g.GroupID = :id")
    })
public class QuizGroup implements Serializable
{
    @Expose
    @Id
    @Column(name="group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int GroupID;
    
    @Expose
    @Column(name="group_name")
    private String GroupName;
    
    @Expose
    @Column(name="group_description")
    private String GroupDescription;
    
    @Expose
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "Group", targetEntity=QuizUser.class, fetch=FetchType.EAGER)
    private List<QuizUser> UserList = new ArrayList<>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="LinkedGroup", targetEntity=QuizEvent.class, fetch=FetchType.LAZY)
    private List<QuizEvent> QuizEvents = new ArrayList<>();
    
    public QuizGroup()
    {

    }
    
    public QuizGroup (String groupName, String groupDescription)
    {
        GroupName = groupName;
        GroupDescription = groupDescription;
    }

    public void setGroupID(int newGroupID)
    {
        GroupID = newGroupID;
    }
    
    public int getGroupID()
    {
        return GroupID;
    }
    
    public void setGroupName(String newGroupName)
    {
        GroupName = newGroupName;
    }
    
    public String getGroupName()
    {
        return GroupName;
    }
    
    public void setGroupDescription(String newGroupDescription)
    {
        GroupDescription = newGroupDescription;
    }
    
    public String getGroupDescription()
    {
        return GroupDescription;
    }

    public void setUsers(List<QuizUser> newUserList)
    {
        UserList = newUserList;
    }
    
    public List<QuizUser> getUsers()
    {
        return UserList;
    }    
    
    public void setQuizEvents(List<QuizEvent> newConfigList)
    {
        QuizEvents = newConfigList;
    }
    
    public List<QuizEvent> getQuizEvents()
    {
        return QuizEvents;
    }
}
