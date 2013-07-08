/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.io.Serializable;

/**
 *
 * @author JWO
 */

@Entity
public class Category implements Serializable
{
    @Id
    private int Id;
    private String Name;
    private String Description;
    
    public Category()
    {
        
    }
    
    public void setName(String newName)
    {
        Name = newName;
    }
    
    public String getName()
    {
        return Name;
    }
    
    public void setDescription(String newDescription)
    {
        Description = newDescription;
    }
    
    public String getDescription()
    {
        return Description;
    }
}
