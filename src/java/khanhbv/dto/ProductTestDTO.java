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
    private String url;

    public ProductTestDTO() {
    }

    public ProductTestDTO(String id, String categoryID, String name, String power, String imageURL, String brandID, String url) {
        this.id = id;
        this.categoryID = categoryID;
        this.name = name;
        this.power = power;
        this.imageURL = imageURL;
        this.brandID = brandID;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }
    
    
}
