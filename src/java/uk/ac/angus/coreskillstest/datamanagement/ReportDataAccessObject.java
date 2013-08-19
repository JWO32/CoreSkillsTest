package uk.ac.angus.coreskillstest.datamanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponseFactory;
import uk.ac.angus.coreskillstest.datamanagement.clientinterface.JSONInterface;
import uk.ac.angus.coreskillstest.entity.Result;
import uk.ac.angus.coreskillstest.entity.jsontypeadaptors.ResultToJSONTypeAdapter;

/**
 *
 * @author JWO
 */
public class ReportDataAccessObject implements JSONInterface
{
    public ReportDataAccessObject()
    {
        
        
    }

    @Override
    public ServerClientResponse addItemJson(String jsonString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse addItemsJson(String jsonArrayString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse getSingleItem(Object itemObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse getSingleItem(int itemId) 
    {
        ServerClientResponse response = new ServerClientResponse();
        QuizEntityManager<Result> em = new QuizEntityManager<>(Result.class);
        String query = "Result.getResultByGroupId";
        HashMap queryParameter = new HashMap();
        GsonBuilder gb = new GsonBuilder();
        gb.excludeFieldsWithoutExposeAnnotation();
        gb.registerTypeAdapter(Result.class, new ResultToJSONTypeAdapter());
        
        Gson g = gb.create();
        
        List<Result> resultList = null;
        String resultListJSON;
        
        queryParameter.put("groupId", new Integer(itemId));
        
        try
        {
            resultList = em.getObjectList(query, queryParameter);
        }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
        {
            System.err.println("Error: Cannot fetch results for group id");
            System.err.println(ex.getMessage());
            response.setResponse(ServerClientResponse.CLIENT_STATUS_ERROR);
            response.setStatusMessage(ServerClientResponseFactory.formatErrorJSON("Database Error", "Cannot fetch results for group"));
            return response;
        }
        
        resultListJSON = g.toJson(resultList, Result.class);
        
        response.setResponse(ServerClientResponse.CLIENT_STATUS_OK);
        response.setClientJson(resultListJSON);
        return response;
    }

    @Override
    public ServerClientResponse getAllItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse deleteSingleItem(int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse deleteMultipleItems(String jsonString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse updateItem(String jsonString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerClientResponse updateItem(String jsonObject, int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
