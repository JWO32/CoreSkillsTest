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
import java.util.Date;

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
    
    /**
     * 
     * @param json
     * @param typeOfT
     * @param context
     * @return
     * @throws JsonParseException 
     */
    @Override
    public QuizEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject obj = json.getAsJsonObject();
        
        QuizEvent qe = new QuizEvent();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Quiz linkedQuiz;
        QuizGroup linkedGroup;
        Date openDate;
        Date closeDate;
        
        int quizId = obj.get("QuizId").getAsInt();
        int groupId = obj.get("GroupId").getAsInt();
        
        linkedQuiz = getQuiz(quizId);
        linkedGroup = getGroup(groupId);
        
        try
        {       
           if(linkedQuiz == null || linkedGroup == null)
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Quiz Event Error: cannot locate quiz resource indicated by QuizEvent");
        
            openDate = sdf.parse(obj.get("OpenDate").getAsString());
            closeDate = sdf.parse(obj.get("CloseDate").getAsString());
            qe.setLinkedQuiz(linkedQuiz);
            qe.setLinkedGroup(linkedGroup);
            qe.setQuizStartDate(openDate);
            qe.setQuizCloseDate(closeDate);
            qe.setNumberOfQuestions(obj.get("NumberOfQuestions").getAsInt());
            qe.setRandomOrder(obj.get("RandomQuestions").getAsBoolean());
            qe.setReturnResult(obj.get("Feedback").getAsBoolean());         
        }catch (ParseException ex)
        {
            System.err.println("Quiz Event Error: cannot parse date");
            
            qe = null;
        }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
        {
            System.err.println("Quiz Event Error: cannot locate resource indicated in Quiz Event");
            qe = null;
        }
        
        return qe;
    }
    
    /**
     * 
     * @param quizId
     * @return 
     */
    private Quiz getQuiz(int quizId)
    {
        QuizDataAccessObject qDAO = new QuizDataAccessObject();
        
        Quiz linkedQuiz = qDAO.getQuizById(quizId);
        
        return linkedQuiz;
    }
    
    /**
     * 
     * @param groupId
     * @return 
     */
    private QuizGroup getGroup(int groupId)
    {
        GroupDataAccessObject gDAO = new GroupDataAccessObject();
        QuizGroup qg = gDAO.fetchGroupByIdObject(groupId);
        
        
        return qg;
    }
}
