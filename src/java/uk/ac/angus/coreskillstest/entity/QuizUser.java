package uk.ac.angus.coreskillstest.entity;

import java.io.Serializable;

import java.util.Calendar;
import javax.persistence.CascadeType;

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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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

@XmlRootElement
public class QuizUser implements Serializable
{ 
    @Id
    @Column(name="user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;
    
    @Column(name="first_name")
    private String FirstName;
   
    @Column(name="last_name")
    private String LastName;
    
    @Column (name="description")
    private String Description;
    
    @Column (name="email")
    private String Email;
    
    @Transient
    private int GroupId;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name="date_added")
    private Calendar DateAdded = Calendar.getInstance();
    
    @ManyToOne(optional=false, targetEntity=UserGroup.class, cascade=CascadeType.PERSIST)
    @JoinColumn(name="group_id", referencedColumnName="group_id")
    @XmlTransient
    private UserGroup Group;
    
    private static final long serialVersionUID = 1L;

    public QuizUser()
    {
       
    }
    
    @XmlAttribute
    public void setUserId(int newID)
    {
        UserId = newID;
    }
    
    public int getUserId()
    {
        return UserId;
    }
    
    @XmlElement
    public void setFirstName(String newUserName)
    {
        FirstName = newUserName;
    }
    
    public String getFirstName()
    {
        return FirstName;
    }
    
    @XmlElement
    public void setLastName(String newLastName)
    {
        LastName = newLastName;
    }
    
    public String getLastName()
    {
        return LastName;
    }
    
    @XmlElement
    public void setUserDescription(String newUserDescription)
    {
        Description = newUserDescription;
    }
    
    public String getUserDescription()
    {
        return Description;
    }
    
    @XmlElement
    public void setUserEmail(String newUserEmail)
    {
        Email = newUserEmail;
    }
    
    public String getUserEmail()
    {
        return Email;
    }
    
    @XmlElement
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
    
    public void setGroup(UserGroup newGroup)
    {
        Group = newGroup;
    }
    
    public UserGroup getGroup()
    {
        return Group;
    }
}
