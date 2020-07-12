/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.dto;

import java.io.Serializable;

/**
 *
 * @author vankhanhbui
 */
public class ProductTestDTO implements Serializable{
    
    private String id;
    private String categoryID;
    private String name;
    private String power;
    private String imageURL;
    private String brandID;
    private String linkProduct;

    public ProductTestDTO() {
    }

    public ProductTestDTO(String categoryID, String name, String power, String imageURL, String linkProduct) {
        this.categoryID = categoryID;
        this.name = name;
        this.power = power;
        this.imageURL = imageURL;
        this.linkProduct = linkProduct;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLinkProduct() {
        return linkProduct;
    }

    public void setLinkProduct(String linkProduct) {
        this.linkProduct = linkProduct;
    }
    
    
}
