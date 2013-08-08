package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import uk.ac.angus.coreskillstest.datamanagement.GroupDataAccessObject;
import uk.ac.angus.coreskillstest.datamanagement.QuizDataAccessObject;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.QuizGroup;
import uk.ac.angus.coreskillstest.quizmanagement.quizconfiguration.QuizEvent;

/**
 *
 * @author JWO
 */

public class QuizEventSerialiseTypeAdapter implements JsonDeserializer<QuizEvent>
{
    public QuizEventSerialiseTypeAdapter()
    {
        
    }
    
    @Override
    public QuizEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException 
    {
        JsonObject obj = json.getAsJsonObject();
        
        QuizEvent qe = new QuizEvent();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        Quiz linkedQuiz;
        QuizGroup linkedGroup;
        
        try
        {
            startCal.setTime(sdf.parse(obj.get("OpenDate").getAsString()));
            endCal.setTime(sdf.parse(obj.get("CloseDate").getAsString()));
        }catch(ParseException ex)
        {
            System.err.println("Parse Error: Unable to convert Quiz Event date");
        }

        
        int quizId = obj.get("QuizId").getAsInt();
        int groupId = obj.get("GroupId").getAsInt();
        
        linkedQuiz = getQuiz(quizId);
        linkedGroup = getGroup(groupId);
        
        //qe.setGroupName(obj.get("GroupName").getAsString());
        //qe.setQuizName(obj.get("QuizName").getAsString());
        qe.setLinkedQuiz(linkedQuiz);
        qe.setLinkedGroup(linkedGroup);
        qe.setQuizStartDate(startCal);
        qe.setQuizCloseDate(endCal);
        qe.setNumberOfQuestions(obj.get("NumberOfQuestions").getAsInt());
        qe.setRandomOrder(obj.get("RandomQuestions").getAsBoolean());
        qe.setReturnResult(obj.get("Feedback").getAsBoolean());

        return qe;
    }
    
    private Quiz getQuiz(int quizId)
    {
        QuizDataAccessObject qDAO = new QuizDataAccessObject();
        
        Quiz linkedQuiz = qDAO.getQuizById(quizId);
        
        return linkedQuiz;
    }
    
    private QuizGroup getGroup(int groupId)
    {
        GroupDataAccessObject gDAO = new GroupDataAccessObject();
        QuizGroup qg = gDAO.fetchGroupByIdObject(groupId);
        
        
        return qg;
    }
}
