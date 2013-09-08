package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

/**
 *
 * @author JWO
 */
@Entity (name="QUIZ_USER")
@NamedQueries({

    @NamedQuery(name="Users.findall", 
        query="SELECT u FROM QUIZ_USER u"),

    @NamedQuery(name="Users.findUserById",
        query="SELECT u FROM QUIZ_USER u WHERE u.UserId = :id"),
    
    @NamedQuery(name="Users.deleteUserById",
        query="DELETE FROM QUIZ_USER u WHERE u.UserId = :id"),
    
    @NamedQuery(name="Users.findUserByEmail",
        query="SELECT u FROM QUIZ_USER u WHERE u.Email=:email")
    })

public class QuizUser implements Serializable
{ 
    @Expose
    @Id
    @Column(name="user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;
    
    @Expose
    @Column(name="first_name")
    private String FirstName;
   
    @Expose
    @Column(name="last_name")
    private String LastName;
    
    @Expose
    @Column (name="description")
    private String Description;
    
    @Expose
    @Column (name="email")
    private String Email;
    
    @Transient
    private int GroupId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="date_added")
    private Calendar DateAdded = Calendar.getInstance();
    
    @ManyToOne(optional=false, targetEntity=QuizGroup.class)
    @JoinColumn(name="group_id", referencedColumnName="group_id")
    private QuizGroup Group;
    
    private static final long serialVersionUID = 1L;

    /**
     * Set the current user id
     * @param newID 
     */
    public void setUserId(int newID)
    {
        UserId = newID;
    }
    
    /**
     * Return the current user id
     * @return 
     */
    public int getUserId()
    {
        return UserId;
    }
    
    /**
     * Set the user's first name
     * @param newUserName 
     */
    public void setFirstName(String newUserName)
    {
        FirstName = newUserName;
    }
    
    /**
     * Get the user's first name
     * @return 
     */
    public String getFirstName()
    {
        return FirstName;
    }
    
    /**
     * Set the users's last name
     * @param newLastName 
     */
    public void setLastName(String newLastName)
    {
        LastName = newLastName;
    }
    
    /**
     * Get the user's last name
     * @return 
     */
    public String getLastName()
    {
        return LastName;
    }
    
    /**
     * Set the user description
     * @param newUserDescription 
     */
    public void setUserDescription(String newUserDescription)
    {
        Description = newUserDescription;
    }
    
    /**
     * Get the user description
     * @return 
     */
    public String getUserDescription()
    {
        return Description;
    }
    
    /**
     * Set user e-mail address
     * @param newUserEmail 
     */
    public void setUserEmail(String newUserEmail)
    {
        Email = newUserEmail;
    }
    
    /**
     * get user e-mail address
     * @return 
     */
    public String getUserEmail()
    {
        return Email;
    }
    
//    @XmlElement
    /**
     * set the date the user was added
     * @param dateAdded 
     */
    public void setUserDateAdded(Calendar dateAdded)
    {
        DateAdded = dateAdded;
    }
    
    /**
     * Get the date the user was added
     * @return 
     */
    public Calendar getUserDateAdded()
    {
        return DateAdded;
    }
    
    /**
     * set the User group id
     * @param newUserGroupId 
     */
    public void setUserGroup(int newUserGroupId)
    {
        GroupId = newUserGroupId;
    }
    
    /**
     * get the user group id
     * @return 
     */
    public int getGroupId()
    {
        return GroupId;
    }
    
    /**
     * Set the group of which the user is a member
     * @param newGroup 
     */
    public void setGroup(QuizGroup newGroup)
    {
        Group = newGroup;
    }
    /**
     * get the group of which the user is a member
     * @return 
     */
    public QuizGroup getGroup()
    {
        return Group;
    }
}
