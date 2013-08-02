/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.datamanagement;

import uk.ac.angus.coreskillstest.entity.QuizGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.List;
 
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author JWO
 */
public class ResultDataAccessObject 
{  
    private EntityManagerFactory ResultManagerFactory;
    
    public ResultDataAccessObject()
    {
        ResultManagerFactory = Persistence.createEntityManagerFactory("CoreSkillsTestPU");
        
    }
    
    public void addResult()
    {
        
    }
    
    public void getResult(int resultId)
    {
        
    }
    
    public void deleteResult(int resultId)
    {
        
    }
}
