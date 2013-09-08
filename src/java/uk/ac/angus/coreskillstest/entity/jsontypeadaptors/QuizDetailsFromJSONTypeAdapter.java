package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import uk.ac.angus.coreskillstest.entity.Question;
import uk.ac.angus.coreskillstest.entity.QuestionOption;
import uk.ac.angus.coreskillstest.entity.Quiz;
import uk.ac.angus.coreskillstest.entity.ResultRule;

/**
 * Constructs a Quiz Object from JSON.
 * 
 * Sets QUIZ properties initially and then iterates into the Question list and
 * question options for each question.
 * @author JWO
 */
public class QuizDetailsFromJSONTypeAdapter implements JsonDeserializer<Quiz>
{
    @Override
    public Quiz deserialize(final JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject QuizObject = json.getAsJsonObject();
        
        Quiz q = new Quiz();

        if(QuizObject.has("QuizId"))
        {
            if(QuizObject.get("QuizId").getAsString().equals("") == false)
                q.setQuizId(QuizObject.get("QuizId").getAsInt());
        }
            
        
        q.setQuizTitle(QuizObject.get("QuizTitle").getAsString());
        q.setQuizSubject(QuizObject.get("QuizSubject").getAsString());
        q.setQuizLevel(QuizObject.get("QuizLevel").getAsString());
        q.setDuration(QuizObject.get("QuizDuration").getAsInt());
        
        processQuestions(q, QuizObject);
        processRule(q, QuizObject);
               
        return q;
    }
    
    private void processRule(final Quiz q, final JsonObject jsonQuiz)
    {
        ResultRule r = new ResultRule();
        
        if(jsonQuiz.get("QuizRule").isJsonObject())
        {
            JsonObject rule = jsonQuiz.getAsJsonObject("QuizRule");
            
            if(rule.has("QuizRuleId"))
            {
                if(rule.get("QuizRuleId").getAsString().equals("") == false)
                    r.setResultRuleId(rule.get("QuizRuleId").getAsInt());
            }
                
            
            r.setQuiz(q);
            r.setRuleName(rule.get("RuleName").getAsString());
            r.setHighMarkBoundary(rule.get("HighMarkBoundary").getAsInt());
            r.setLowMarkBoundary(rule.get("LowMarkBoundary").getAsInt());
            r.setFeedback(rule.get("Category").getAsString());
            r.setPassFail(rule.get("PassFail").getAsBoolean());

            q.getResultRules().add(r);
        }
    }
    
    private void processQuestions(final Quiz quiz, final JsonObject jsonQuiz)
    {
        final List<Question> questions = new ArrayList<>();
        final JsonArray questionArray = jsonQuiz.get("Questions").getAsJsonArray();
        
        for(int i = 0; i < questionArray.size(); i++)
        {
            JsonObject currentQuestion  = questionArray.get(i).getAsJsonObject();
                          
            String questionText = currentQuestion.get("QuestionText").getAsString();
            String questionLevel = currentQuestion.get("QuestionLevel").getAsString();
            int questionScore = currentQuestion.get("QuestionScore").getAsInt();
            
            Question newQuestion = new Question();
            
            if(currentQuestion.has("QuestionId"))
            {
                if(currentQuestion.get("QuestionId").getAsString().equals("") == false)
                    newQuestion.setQuestionId(currentQuestion.get("QuestionId").getAsInt());
            }
            
            if(questionText != null)
                newQuestion.setQuestiontext(questionText);
            if(questionLevel != null)
                newQuestion.setQuestionLevel(questionLevel);
            
            newQuestion.setMark(questionScore);
            newQuestion.setQuiz(quiz);
           
           processQuestionOptions(newQuestion, currentQuestion);
           
           questions.add(newQuestion);    
        }       
        quiz.setQuestions(questions);        
    }
   
    private void processQuestionOptions(final Question question, final JsonObject jsonOptions)
    {
        final List<QuestionOption> options = new ArrayList<>();
        final JsonArray optionArray = jsonOptions.get("QuestionOptions").getAsJsonArray();
        
        for(int i = 0; i < optionArray.size(); i++)
        {
            JsonObject currentOption = optionArray.get(i).getAsJsonObject();
            QuestionOption newOption = new QuestionOption();
            
            if(currentOption.has("QuestionOptionId"))
            {
                if(currentOption.get("QuestionOptionId").getAsString().equals("") == false)
                    newOption.setOptionId(currentOption.get("QuestionOptionId").getAsInt());
            }
                
            String optionText = currentOption.get("OptionText").getAsString();
            boolean optionCorrect = currentOption.get("CorrectOption").getAsBoolean();
            
            if(optionText!=null)
                newOption.setOptionText(optionText);
            
            newOption.setCorrectOption(optionCorrect);
            newOption.setQuestion(question);
            
            options.add(newOption);
        }        
        question.setOptions(options);
    }
}
