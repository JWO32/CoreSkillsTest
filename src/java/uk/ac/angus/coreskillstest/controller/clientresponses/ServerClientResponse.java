/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.angus.coreskillstest.controller.clientresponses;

/**
 *
 * @author JWO
 */
public class ServerClientResponse 
{

    public static final int CLIENT_STATUS_OK = 1;
    public static final int CLIENT_STATUS_ERROR = 2;
    public static final int CLIENT_STATUS_RESPONSE = 3;
    public static final int CLIENT_INTERNAL_ERROR = 4;
    
    private int ResponseType;
    private String JsonStatusMessage;
    private String ClientJson;
    
    public ServerClientResponse()
    {
        ClientJson = ServerClientResponseFactory.EMPTY_JSON_OBJECT;
        JsonStatusMessage = ServerClientResponseFactory.EMPTY_JSON_OBJECT;
    }
    
    public ServerClientResponse(boolean opSuccess, boolean status, String response)
    {
        JsonStatusMessage = response;
    }
    
    public void setResponse(int newResponse)
    {
        if(newResponse < 1 || newResponse > 3)
            ResponseType = ServerClientResponse.CLIENT_INTERNAL_ERROR;
        else
            ResponseType = newResponse;
    }
    
    public int getResponse()
    {
        return ResponseType;
    }
    
    public void setStatusMessage(String jsonResponse)
    {
        JsonStatusMessage = jsonResponse;
    }
    
    public String getStatusMessage()
    {
        return JsonStatusMessage;
    }
    
    public void setClientJson(String json)
    {
        ClientJson = json;
    }
    
    public String getClientJson()
    {
        return ClientJson;
    }
}
