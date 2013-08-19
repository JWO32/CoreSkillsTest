
package uk.ac.angus.coreskillstest.datamanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

 
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.datamanagement.clientinterface.JSONInterface;

import uk.ac.angus.coreskillstest.entity.Result;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.ResultToJSONTypeAdapter;

/**
 *
 * TODO: Refactor to implement the JSON interface.
 * 
 * @author JWO
 */
public class ResultDataAccessObject implements JSONInterface<Result>
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
        gb.registerTypeAdapter(Result.class, new ResultToJSONTypeAdapter());
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

    @Override
    public ServerClientResponse addItemJson(String jsonString) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse addItemsJson(String jsonArrayString) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse getSingleItem(Result itemObject) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse getAllItems() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse deleteSingleItem(int itemId) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse deleteMultipleItems(String jsonString) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse updateItem(String jsonString) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse getSingleItem(int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse updateItem(String jsonObject, int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
