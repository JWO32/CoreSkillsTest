package uk.ac.angus.coreskillstest.entity.jsontypeadaptors;

import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import uk.ac.angus.coreskillstest.entity.QuizGroup;
/**
 *
 * @author JWO
 */
public class GroupDeserialiseTypeAdaptor implements JsonSerializer<QuizGroup>
{
    public GroupDeserialiseTypeAdaptor()
    {
        
    }

    @Override
    public JsonElement serialize(QuizGroup src, Type typeOfSrc, JsonSerializationContext context) 
    {
        JsonObject jo = new JsonObject();
        
        jo.addProperty("GroupName", src.getGroupName());
        jo.addProperty("GroupId", src.getGroupID());
       
        return jo;
    }
}
