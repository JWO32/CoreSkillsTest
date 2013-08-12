/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.controller.clientresponses;

/**
 *
 * Convenience class to return messages in a standard JSON format.
 * 
 * @author JWO
 */
public final class ServerClientResponseFactory 
{
    public static final String EMPTY_JSON_OBJECT = "{}";
    public static final String EMPTY_JSON_ARRAY = "{[]}";
    
    public static String formatErrorJSON(String errorType, String errorMessage)
    {
        ServerClientResponse resp = new ServerClientResponse(true, false, "{\"Status\":"+errorType+", \"Message\":"+errorMessage+ "}");
        
        return "{\"Status\":\""+errorType+"\", \"Message\":\""+errorMessage+ "\"}";
    }
    
    public static String formatStatusJSON(String statusType, String statusMessage)
    {
        ServerClientResponse resp = new ServerClientResponse(false, true, "{\"Status\":"+statusType+", \"Message\":"+statusMessage+ "}" );
        return "{\"Status\":\""+statusType+"\", \"Message\":\""+statusMessage+ "\"}";
    }
    
    public static String formatSuccessJSON(String successType, String successMessage)
    {
        ServerClientResponse resp = new ServerClientResponse(true, false, "{\"Status\":"+successType+", \"Message\":"+successMessage+ "}");
        return "{\"Status\":\""+successType+"\", \"Message\":\""+successMessage+ "\"}";
    }
    
    public static String getEmptyJSONObject()
    {        
        return "{}";
    }
    
    public static String getEmptyJSONArray()
    {
        return"{[]}";
    }
}
