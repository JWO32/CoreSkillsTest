package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;

/**
 *
 * @author james
 */
public class QuizEventDetailsToJSONTypeAdapter implements JsonSerializer<QuizEvent>
{

    @Override
    public JsonElement serialize(QuizEvent src, Type typeOfSrc, JsonSerializationContext context) 
    {
       JsonObject jo = new JsonObject();
       DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       
       Date openDate = src.getQuizStartDate();
       Date closeDate = src.getQuizCloseDate();
       //
       // Construct Json Object
       //
       jo.addProperty("QuizEventId", src.getQuizConfigId());
       jo.addProperty("NumberOfQuestions", src.getNumberOfQuestions());
       jo.addProperty("RandomQuestions", src.getRandomOrder());
       jo.addProperty("OpenDate", sdf.format(openDate));
       jo.addProperty("CloseDate", sdf.format(closeDate));
       jo.addProperty("Feedback", src.getReturnResult());
       jo.addProperty("QuizName", src.getLinkedQuiz().getQuizTitle());
       jo.addProperty("GroupName", src.getLinkedGroup().getGroupName());
       jo.addProperty("QuizId", src.getLinkedQuiz().getQuizId());
       jo.addProperty("GroupId", src.getLinkedGroup().getGroupID());
       jo.addProperty("UserId", src.getUserId());
       
       return jo;       
    }
}
