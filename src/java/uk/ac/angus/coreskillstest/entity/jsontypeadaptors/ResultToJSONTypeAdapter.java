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
public class ResultToJSONTypeAdapter implements JsonSerializer<Result>
{
    public ResultToJSONTypeAdapter()
    {
        
    }
    
    @Override
    public JsonElement serialize(Result src, Type typeOfSrc, JsonSerializationContext context) 
    {
        JsonObject jo = new JsonObject();
        
        jo.addProperty("FirstName", src.getQuizUser().getFirstName());
        jo.addProperty("LastName", src.getQuizUser().getLastName());
        jo.addProperty("QuizScore", src.getQuizScore());
        jo.addProperty("QuizPercentage", src.getQuizPercentage());
        jo.addProperty("Feedback", src.getLinkedFeedback());
        jo.addProperty("NumberOfQuestions", src.getLinkedQuiz().getNumberOfQuestions());
        
        return jo;
    }
}
