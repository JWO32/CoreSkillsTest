package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class UserGroup
 * 
 * Manages a collection of users that form a class or group in the quiz system
 * @author JWO
 */
@Entity(name="QUIZ_GROUP")
@XmlRootElement(name="UserGroup")
public class UserGroup implements Serializable
{
    @Id
    @Column(name="group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int GroupID;
    
    @Column(name="group_name")
    private String GroupName;
    
    @Column(name="group_description")
    private String GroupDescription;
    
    @OneToMany (mappedBy = "UserGroup", targetEntity=QuizUser.class, fetch=FetchType.EAGER)
   
    private ArrayList<QuizUser> UserList = new ArrayList<>();
    
    public UserGroup()
    {

    }
    
    @XmlAttribute
    public void setGroupID(int newGroupID)
    {
        GroupID = newGroupID;
    }
    
    public int getGroupID()
    {
        return GroupID;
    }
    
    @XmlElement
    public void setGroupName(String newGroupName)
    {
        GroupName = newGroupName;
    }
    
    public String getGroupName()
    {
        return GroupName;
    }
    
    @XmlElement
    public void setGroupDescription(String newGroupDescription)
    {
        GroupDescription = newGroupDescription;
    }
    
    public String getGroupDescription()
    {
        return GroupDescription;
    }
    
    @XmlElement(name="User")
    public void setUserList(ArrayList<QuizUser> newUserList)
    {
        UserList = newUserList;
    }
    
    public ArrayList<QuizUser> getUserList()
    {
        return UserList;
    }    
}
