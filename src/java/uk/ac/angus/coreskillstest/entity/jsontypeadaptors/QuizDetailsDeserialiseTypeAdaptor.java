package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import uk.ac.angus.coreskillstest.entity.Quiz;

/**
 *
 * @author JWO
 */
public class QuizDetailsDeserialiseTypeAdaptor implements JsonSerializer<Quiz>
{
    @Override
    public JsonElement serialize(Quiz src, Type typeOfSrc, JsonSerializationContext context) 
    {
        JsonObject obj = new JsonObject();
              
        obj.addProperty("QuizId", src.getQuizId());
        obj.addProperty("QuizTitle", src.getQuizTitle());
        obj.addProperty("NumberOfQuestions", src.getNumberOfQuestions());
        
        return obj;
    } 
}
