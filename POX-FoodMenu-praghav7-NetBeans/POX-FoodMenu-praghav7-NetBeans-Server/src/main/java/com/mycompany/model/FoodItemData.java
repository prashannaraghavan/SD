/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Prashanna
 */

@XmlRootElement(name = "FoodItemData")
//@XmlAccessorType(XmlAccessType.FIELD)

public class FoodItemData {

    
    private FoodItem[] foodItems;

    @XmlElement(name="FoodItem")
    public FoodItem[] getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(FoodItem[] foodItems) {
        this.foodItems = foodItems;
    }
    
}
