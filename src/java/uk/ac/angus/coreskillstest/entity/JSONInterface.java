package uk.ac.angus.coreskillstest.entity;

import java.util.List;

/**
 *
 * @author JWO
 */
public interface JSONInterface<T>
{
    void addItemJson(String jsonString);
    
    void addItemsJson(String jsonArrayString);
    
    String getSingleItem(T itemObject);
    
    String getAllItems(List<T> itemObjects);
    
}
