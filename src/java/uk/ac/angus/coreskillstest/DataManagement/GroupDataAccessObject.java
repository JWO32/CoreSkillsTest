/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.DataManagement;

import com.google.gson.Gson;
 
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JWO
 */
public class GroupDataAccessObject 
{
    private EntityManagerFactory groupDataFactory;
    private Gson jsonSerialiser;
    
    
    public GroupDataAccessObject()
    {
        groupDataFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        jsonSerialiser = new Gson();
    }
    
    public void addSingleGroup(String singleGroupJson)
    {
        
    }
    
    public void addMultipleGroups(String multipleGroupJson)
    {
        
    }
    
    public void deleteSingleGroup(int groupId)
    {
        
    }
    
    public void deleteMultipleGroups(int[] groupIdList)
    {
        
    }
    
    public String fetchGroupById(int groupId)
    {
        String json = "";
        
        return json;
    }
    
    public String fetchAllGroups()
    {
        String json = "";
        
        
        return json;
    }    
}
