/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.model.FoodItem;
import com.mycompany.model.FoodItemData;
import static com.mycompany.service.FoodItemService.autoId;
import static com.mycompany.service.FoodItemService.foodItem;
import java.io.File;
import java.util.Iterator;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Web application lifecycle listener.
 *
 * @author Prashanna
 */
public class NewServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        for(int i=0;i<10;i++)
//        {
//            FoodItem foods = new FoodItem();
//            foods.setId(i+1);
//            foods.setName("Steak and Kidney Pie");
//            foods.setDescription("Tender cubes of steak, with tender lamb kidney is succulent rich gravy. Served with a side of mashed potatoes and peas.");
//            foods.setCategory("Dinner");
//            foods.setPrice(15.95);
//            
//            foodItem.put(foods.getId(),foods);
//        }

        try 
        {
            File file = new File(sce.getServletContext().getRealPath("FoodItemData.xml"));
            JAXBContext jaxbContext = JAXBContext.newInstance(FoodItemData.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            FoodItemData foods = (FoodItemData) jaxbUnmarshaller.unmarshal(file);
            System.out.println(foods);

            for(FoodItem food : foods.getFoodItems())
            {
                System.out.println("Test2");
                foodItem.put(food.getId(),food);
                
                if(autoId < food.getId())
                {
                    autoId = food.getId();
                }
            }
            
            //autoId += 1;
	} 
        
        catch (JAXBException e) 
        {
            e.printStackTrace();
	}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
