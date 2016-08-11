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
@XmlRootElement(name = "NewFoodItems",namespace = "http://cse564.asu.edu/PoxAssignment")
public class NewFoodItem {
    private FoodItem[] foodItems;

    @XmlElement(name="FoodItem")
    public FoodItem[] getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(FoodItem[] foodItems) {
        this.foodItems = foodItems;
    }
}
