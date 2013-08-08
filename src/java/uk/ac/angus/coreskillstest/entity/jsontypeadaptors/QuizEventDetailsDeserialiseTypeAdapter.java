package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Locale;
import uk.ac.angus.coreskillstest.datamanagement.GroupDataAccessObject;
import uk.ac.angus.coreskillstest.datamanagement.QuizDataAccessObject;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.QuizGroup;
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
       //jo.addProperty("OpenDate", src.getQuizStartDate().getDisplayName(field, style, Locale.UK));
       //jo.addProperty("CloseDate", src.getQuizCloseDate());
       jo.addProperty("Feedback", src.getReturnResult());
       jo.addProperty("QuizName", src.getLinkedQuiz().getQuizTitle());
       jo.addProperty("GroupName", src.getLinkedGroup().getGroupName());
       jo.addProperty("QuizId", src.getLinkedQuiz().getQuizId());
       jo.addProperty("GroupId", src.getLinkedGroup().getGroupID());
       
       return jo;       
    }
}
