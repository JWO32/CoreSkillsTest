package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;

/**
 *
 * @author james
 */
public class QuizEventDetailsDeserialiseTypeAdapter implements JsonSerializer<QuizEvent>
{

    @Override
    public JsonElement serialize(QuizEvent src, Type typeOfSrc, JsonSerializationContext context) 
    {
       JsonObject jo = new JsonObject();
       
       jo.addProperty("QuizEventId", src.getQuizConfigId());
       jo.addProperty("NumberOfQuestions", src.getNumberOfQuestions());
       jo.addProperty("Randomise", src.getRandomOrder());
       //jo.addProperty("OpenDate", src.getQuizStartTime());
       //jo.addProperty("CloseDate", src.getQuizCloseTime());
       jo.addProperty("Feedback", src.getReturnResult());
       
       return jo;       
    }
    
}
