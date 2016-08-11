/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.model.FoodItem;
import com.mycompany.model.NewFoodItem;
import com.mycompany.model.SelectedFoodItems;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Prashanna
 */

@Path("restservices")

public class FoodItemService {
    
    public static Map<Integer,FoodItem> foodItem = new HashMap<Integer,FoodItem>();
    public static int autoId = 0;
    
//    static
//    {
//        
//        System.out.println(foodItem);
//        
//    }
    
    @POST
    @Path("/FoodItem")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    
    public Response consumesXMLFormat(String xmlString) throws JAXBException
    {
        System.out.println(xmlString);
        Response response = null;
        
        if(xmlString.contains("SelectedFoodItems"))
        {
            try
            {
                JAXBContext jaxbContext = JAXBContext.newInstance(SelectedFoodItems.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

                StringReader reader = new StringReader(xmlString);
                SelectedFoodItems selectedFoodItems = (SelectedFoodItems) unmarshaller.unmarshal(reader);

                StringBuilder stringBuilder = new StringBuilder("<RetrievedFoodItems xmlns=”http://cse564.asu.edu/PoxAssignment”>\n");
                
                for(int id : selectedFoodItems.getFoodItemId())
                {
                    if(foodItem.get(id) != null)
                    {
                        stringBuilder.append("<FoodItem country=").append("\"").append(foodItem.get(id).getCountry()).append("\"").append(">\n");
                        stringBuilder.append("<id>").append(foodItem.get(id).getId()).append("</id>\n");
                        stringBuilder.append("<name>").append(foodItem.get(id).getName()).append("</name>\n");
                        stringBuilder.append("<description>").append(foodItem.get(id).getDescription()).append("</description>\n");
                        stringBuilder.append("<category>").append(foodItem.get(id).getCategory()).append("</category>\n");
                        stringBuilder.append("<price>").append(foodItem.get(id).getPrice()).append("</price>\n");
                        stringBuilder.append("</FoodItem>\n");
                        
                    }
                     
                    else
                    {
                        stringBuilder.append("<InvalidFoodItem>\n");
                        stringBuilder.append("<FoodItemId>").append(id).append("</FoodItemId>\n");
      
                        stringBuilder.append("</InvalidFoodItem>\n");
                    }
                }
                
                stringBuilder.append("</RetrievedFoodItems>\n");
                
                
                response = Response.status(Response.Status.OK).entity(stringBuilder.toString()).build();
                
                   
            }

            catch(JAXBException e)
            {
                String errorMessage = "<InvalidMessage xmlns=”http://cse564.asu.edu/PoxAssignment”/>\n";
                response = Response.status(Response.Status.OK).entity(errorMessage).build();
            }
        }
        
        else if(xmlString.contains("NewFoodItems")) 
        {
            try
            {
                JAXBContext jaxbContext = JAXBContext.newInstance(NewFoodItem.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

                StringReader reader = new StringReader(xmlString);
                NewFoodItem newFoodItem = (NewFoodItem) unmarshaller.unmarshal(reader);

                StringBuilder stringBuilder = new StringBuilder();
                
                for(FoodItem item : newFoodItem.getFoodItems())
                {
                    if(item.getCountry()!=null && item.getName()!=null 
                            && item.getDescription()!=null && item.getCategory()!=null && item.getPrice()!=null)
                    {
                        
                        Iterator it = foodItem.entrySet().iterator();
                        int index = -1;
                        while (it.hasNext())
                        {
                            Map.Entry pair = (Map.Entry)it.next();
                            FoodItem value = (FoodItem) pair.getValue();
                           
                            if(item.getCountry().equals(value.getCountry()) 
                                        && item.getName().equals(value.getName())
                                        /*&& item.getDescription().equals(value.getDescription())
                                        && item.getCategory().equals(value.getCategory())
                                        && Objects.equals(item.getPrice(), value.getPrice())*/)
                                                {
                                                    index = value.getId();                      
                                                }
                            
                           //it.remove();
                        }
                        
                        if (index == -1){
                           stringBuilder.append("<FoodItemAdded xmlns=”http://cse564.asu.edu/PoxAssignment”>\n");
                            item.setId(autoId++);
                            foodItem.put(item.getId(),item);

                            stringBuilder.append("<FoodItemId>").append(item.getId()).append("</FoodItemId>\n");
                            stringBuilder.append("</FoodItemAdded>\n");
                                    
                        }else{
                            stringBuilder.append("<FoodItemExists xmlns=”http://cse564.asu.edu/PoxAssignment”>\n");
                                    stringBuilder.append("<FoodItemId>").append(index).append("</FoodItemId>\n");
                                    stringBuilder.append("</FoodItemExists>\n");
                        }
   
                            
                        }
                    
                    else
                    {
                        stringBuilder.append("<InvalidMessage xmlns=”http://cse564.asu.edu/PoxAssignment”>\n");
                        stringBuilder.append("</InvalidMessage>\n");
                    }
                }
                
                response = Response.status(Response.Status.OK).entity(stringBuilder.toString()).build();
    
            }

            catch(JAXBException e)
            {
                e.printStackTrace();
                String errorMessage = "<InvalidMessage xmlns=”http://cse564.asu.edu/PoxAssignment”/>\n";
                response = Response.status(Response.Status.OK).entity(errorMessage).build();
            }
        }
        
        return response; 
        
    }
    
    
    @GET
    @Path("/FoodItem/getFoodItemByXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String getFoodItemByXML(@PathParam("id")int id)
    {
        //FoodItem foodItemObject = foodItem.get(id);
        StringBuilder stringBuilder = new StringBuilder("<RetrievedFoodItems xmlns=”http://cse564.asu.edu/PoxAssignment”>\n");
        
        stringBuilder.append("<FoodItem country=").append("\"").append(foodItem.get(id).getCountry()).append("\"").append(">\n");
        stringBuilder.append("<id>").append(foodItem.get(id).getId()).append("</id>\n");
        stringBuilder.append("<name>").append(foodItem.get(id).getName()).append("</name>\n");
        stringBuilder.append("<description>").append(foodItem.get(id).getDescription()).append("</description>\n");
        stringBuilder.append("<category>").append(foodItem.get(id).getCategory()).append("</category>\n");
        stringBuilder.append("<price>").append(foodItem.get(id).getPrice()).append("</price>\n");
        stringBuilder.append("</FoodItem>\n");
        stringBuilder.append("</RetrievedFoodItems>\n");
        return stringBuilder.toString();
    }
}
