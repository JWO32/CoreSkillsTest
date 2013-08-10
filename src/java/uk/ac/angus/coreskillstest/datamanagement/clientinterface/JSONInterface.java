package uk.ac.angus.coreskillstest.datamanagement.clientinterface;

import java.util.List;
import uk.ac.angus.coreskillstest.controller.clientresponses.ServerClientResponse;

/**
 *
 * @author JWO
 */
public interface JSONInterface<T>
{
    ServerClientResponse addItemJson(String jsonString);
    
    ServerClientResponse addItemsJson(String jsonArrayString);
    
    ServerClientResponse getSingleItem(T itemObject);
    
    ServerClientResponse getAllItems();
    
    ServerClientResponse deleteSingleItem(String jsonString);
    
    ServerClientResponse deleteMultipleItems(String jsonString);
    
    ServerClientResponse updateItem(String jsonString);
}