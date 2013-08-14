
package uk.ac.angus.coreskillstest.datamanagement;

import uk.ac.angus.coreskillstest.entity.Result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.util.List;
 
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import uk.ac.angus.coreskillstest.entity.Result;

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
    
    public static String getResultObjectasJSON(Result resultObj)
    {
        String resultJson;
        
        GsonBuilder gb = new GsonBuilder();
        Gson gsn = gb.excludeFieldsWithoutExposeAnnotation().create();
        
        resultJson = gsn.toJson(resultObj, Result.class);
        
        return resultJson;             
    }
    
    public void addResultObject(Result result)
    {
        EntityManager em = ResultManagerFactory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(result);
        em.getTransaction().commit();
        em.close();     
    }
    
    public Result getResult(int resultId)
    {
        EntityManager em = ResultManagerFactory.createEntityManager();
        Result res;
        
        res = (Result) em.find(Result.class, resultId);
        
        return res; 
    }
    
    public boolean deleteResult(int resultId)
    {
        
        return true;
    }
}
