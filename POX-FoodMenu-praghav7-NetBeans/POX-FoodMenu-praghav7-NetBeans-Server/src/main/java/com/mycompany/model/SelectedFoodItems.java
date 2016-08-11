/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Prashanna
 */
@XmlRootElement(name = "SelectedFoodItems",namespace = "http://cse564.asu.edu/PoxAssignment")
public class SelectedFoodItems {
    
    private int[] foodItemId;  

    @XmlElement(name="FoodItemId")
    
    public int[] getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(int[] foodItemId) {
        this.foodItemId = foodItemId;
    }

    
    
    
    
}
