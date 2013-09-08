package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class QuizEventFromJSONTypeAdapter implements JsonDeserializer<QuizEvent>
{  
    /**
     * 
     * @param json
     * @param typeOfT
     * @param context
     * @return
     * @throws JsonParseException 
     */
    @Override
    public QuizEvent deserialize(final JsonElement json,final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        
        QuizEvent quizEvent = new QuizEvent();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Quiz linkedQuiz;
        QuizGroup linkedGroup;
        Date openDate;
        Date closeDate;
        
        int quizId = jsonObject.get("QuizId").getAsInt();
        int groupId = jsonObject.get("GroupId").getAsInt();
        
        linkedQuiz = getQuiz(quizId);
        linkedGroup = getGroup(groupId);
        
        try
        {       
           if(linkedQuiz == null || linkedGroup == null)
           {
            throw new uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException("Quiz Event Error: cannot locate quiz resource indicated by QuizEvent");
           }
           
            openDate = simpleDateFormat.parse(jsonObject.get("OpenDate").getAsString());
            closeDate = simpleDateFormat.parse(jsonObject.get("CloseDate").getAsString());
            quizEvent.setLinkedQuiz(linkedQuiz);
            quizEvent.setLinkedGroup(linkedGroup);
            quizEvent.setQuizStartDate(openDate);
            quizEvent.setQuizCloseDate(closeDate);
            quizEvent.setNumberOfQuestions(jsonObject.get("NumberOfQuestions").getAsInt());
            quizEvent.setRandomOrder(jsonObject.get("RandomQuestions").getAsBoolean());
            quizEvent.setReturnResult(jsonObject.get("Feedback").getAsBoolean());         
        }catch (ParseException ex)
        {
            System.err.println("Quiz Event Error: cannot parse date");
            System.err.println(ex.getMessage());
            quizEvent = null;
        }catch(uk.ac.angus.coreskillstest.quizmanagement.exception.QuizResourceNotFoundException ex)
        {
            System.err.println("Quiz Event Error: cannot locate resource indicated in Quiz Event");
            quizEvent = null;
        }
        
        return quizEvent;
    }
    
    /**
     * 
     * @param quizId
     * @return 
     */
    private Quiz getQuiz(int quizId)
    {
        QuizDataAccessObject qDAO = new QuizDataAccessObject();
        
        Quiz linkedQuiz = qDAO.getQuizObjectById(quizId);
        
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
