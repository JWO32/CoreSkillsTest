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
        query="DELETE FROM QUIZ_USER u WHERE u.UserId = :id")
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
//    @XmlTransient
    private QuizGroup Group;
    
    private static final long serialVersionUID = 1L;

    public QuizUser()
    {
       
    }
    
//    @XmlAttribute
    public void setUserId(int newID)
    {
        UserId = newID;
    }
    
    public int getUserId()
    {
        return UserId;
    }
    
//    @XmlElement
    public void setFirstName(String newUserName)
    {
        FirstName = newUserName;
    }
    
    public String getFirstName()
    {
        return FirstName;
    }
    
//    @XmlElement
    public void setLastName(String newLastName)
    {
        LastName = newLastName;
    }
    
    public String getLastName()
    {
        return LastName;
    }
    
//    @XmlElement
    public void setUserDescription(String newUserDescription)
    {
        Description = newUserDescription;
    }
    
    public String getUserDescription()
    {
        return Description;
    }
    
//    @XmlElement
    public void setUserEmail(String newUserEmail)
    {
        Email = newUserEmail;
    }
    
    public String getUserEmail()
    {
        return Email;
    }
    
//    @XmlElement
    public void setUserDateAdded(Calendar dateAdded)
    {
        DateAdded = dateAdded;
    }
    
    public Calendar getUserDateAdded()
    {
        return DateAdded;
    }
    
    public void setUserGroup(int newUserGroupId)
    {
        GroupId = newUserGroupId;
    }
    
    public int getUserGroup()
    {
        return GroupId;
    }
    
    public void setGroup(QuizGroup newGroup)
    {
        Group = newGroup;
    }
    
    public QuizGroup getGroup()
    {
        return Group;
    }
}
