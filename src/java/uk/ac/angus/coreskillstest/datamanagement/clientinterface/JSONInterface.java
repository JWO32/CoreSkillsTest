package uk.ac.angus.coreskillstest.datamanagement.clientinterface;

import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;

/**
 *
 * Public interface to data management objects.  Ensure that data is returned to
 * the client in a standard way using the ClientServerResponse object and JSON.
 * 
 * Overloaded methods are provided because there is the possibility of using different
 * database accesses, e.g. finding an object by id, JSON or an instantiated object.
 * 
 * TODO: Investigate possibility of using an Abstract class
 * @author JWO
 */
public interface JSONInterface <T>
{
    /**
     * Add a single object to the database using the JSON string specified.
     * @param jsonString
     * @return 
     */
    ServerClientResponse addItemJson(String jsonString);
    
    /**
     * Add multiple items to the database in the form of a JSON array
     * @param jsonArrayString
     * @return 
     */
    ServerClientResponse addItemsJson(String jsonArrayString);
    
    /**
     * Get an item from the database that matches the object provided
     * @param itemObject
     * @return 
     */
    ServerClientResponse getSingleItem(T itemObject);
    
    /**
     * find an item from the database with the id specified
     * @param itemId
     * @return 
     */
    ServerClientResponse getSingleItem(int itemId);
    
    /**
     * Return all relevant objects from the database
     * @return 
     */
    ServerClientResponse getAllItems();
    
    /**
     * Delte a single item from the database with the specified ID
     * @param itemId
     * @return 
     */
    ServerClientResponse deleteSingleItem(int itemId);
    
    /**
     * Delete multiple items from the database using the JSON string supplied
     * @param jsonString
     * @return 
     */
    ServerClientResponse deleteMultipleItems(String jsonString);
    
    
    /**
     * Perform an edit operation on the JSON specified object
     * @param jsonString
     * @return 
     */
    ServerClientResponse updateItem(String jsonString);
    
    /**
     * Update the object specified by the id number.  The edited item must be
     * in JSON form and supplied as the jsonObject parameter.
     * @param jsonObject 
     * @param itemId
     * @return 
     */
    ServerClientResponse updateItem(String jsonObject, int itemId);
}
