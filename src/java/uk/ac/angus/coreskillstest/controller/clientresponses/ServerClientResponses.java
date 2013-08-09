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
public final class ServerClientResponses 
{
    public static String formatErrorJSON(String errorType, String errorMessage)
    {
        
        return "{\"Status\":"+errorType+", \"Message\":"+errorMessage+ "}";
    }
    
    public static String formatStatusJSON(String statusType, String statusMessage)
    {
        
        return "{\"Status\":"+statusType+", \"Message\":"+statusMessage+ "}";
    }
    
    public static String formatSuccessJSON(String successType, String successMessage)
    {
        
        return "{\"Status\":"+successType+", \"Message\":"+successMessage+ "}";
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
