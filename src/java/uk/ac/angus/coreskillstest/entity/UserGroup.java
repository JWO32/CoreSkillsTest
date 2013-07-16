package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;
import java.util.ArrayList;

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

//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class UserGroup
 * 
 * Manages a collection of users that form a class or group in the quiz system
 * @author JWO
 */
@Entity(name="QUIZ_GROUP")
@NamedQueries({
    @NamedQuery(name="Groups.getAllGroupsAndUsers", 
        query="SELECT g from QUIZ_GROUP g INNER JOIN g.UserList u"),
    
    @NamedQuery(name="Groups.getAllGroups", 
        query="SELECT g from QUIZ_GROUP g"),

    @NamedQuery(name="Groups.findGroupById",
        query="SELECT g from QUIZ_GROUP g WHERE g.GroupID = :id"),
    
    @NamedQuery(name="Groups.deleteGroupById",
        query="DELETE FROM QUIZ_GROUP g WHERE g.GroupID = :id")
    })

//@XmlRootElement(name="UserGroup")
public class UserGroup implements Serializable
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
    private ArrayList<QuizUser> UserList = new ArrayList<>();
    
    public UserGroup()
    {

    }
    
    public UserGroup (String groupName, String groupDescription)
    {
        GroupName = groupName;
        GroupDescription = groupDescription;
    }
    
   // @XmlAttribute
    public void setGroupID(int newGroupID)
    {
        GroupID = newGroupID;
    }
    
    public int getGroupID()
    {
        return GroupID;
    }
    
   // @XmlElement
    public void setGroupName(String newGroupName)
    {
        GroupName = newGroupName;
    }
    
    public String getGroupName()
    {
        return GroupName;
    }
    
   // @XmlElement
    public void setGroupDescription(String newGroupDescription)
    {
        GroupDescription = newGroupDescription;
    }
    
    public String getGroupDescription()
    {
        return GroupDescription;
    }
    
  //  @XmlElement(name="User")
    public void setUserList(ArrayList<QuizUser> newUserList)
    {
        UserList = newUserList;
    }
    
    public ArrayList<QuizUser> getUserList()
    {
        return UserList;
    }    
}
