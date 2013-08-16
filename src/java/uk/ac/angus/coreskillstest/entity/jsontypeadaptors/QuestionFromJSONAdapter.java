/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import uk.ac.angus.coreskillstest.entity.Question;

/**
 *
 * @author JWO
 */
public class QuestionFromJSONAdapter implements JsonDeserializer<Question>
{
    @Override
    public Question deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException
    {
        Question q = new Question();
 
        return q;      
    }
    
}
