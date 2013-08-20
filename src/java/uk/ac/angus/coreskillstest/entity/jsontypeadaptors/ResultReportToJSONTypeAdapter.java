/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

import uk.ac.angus.coreskillstest.entity.Result;

/**
 *
 * @author JWO
 */
public class ResultReportToJSONTypeAdapter implements JsonSerializer<Result>
{

    public ResultReportToJSONTypeAdapter()
    {
        
    }
    
    @Override
    public JsonElement serialize(Result src, Type typeOfSrc, JsonSerializationContext context) 
    {
        JsonObject jo = new JsonObject();
        
        jo.addProperty("FirstName", src.getQuizUser().getFirstName());
        jo.addProperty("LastName", src.getQuizUser().getLastName());
        jo.addProperty("QuizTitle", src.getLinkedQuiz().getQuizTitle());
        jo.addProperty("Score", src.getQuizScore());
        jo.addProperty("Percentage", src.getQuizPercentage());
        jo.addProperty("Category", src.getLinkedFeedback());
        
        return jo;
    }
}
