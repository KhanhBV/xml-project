/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.dto;

import java.util.HashMap;
import java.util.Map;
import khanhbv.dlo.ProductBLO;
import khanhbv.entities.Product;

/**
 *
 * @author vankhanhbui
 */
public class ProductCart {

    private int quantity;
    private int time;

    public ProductCart() {
    }

    public ProductCart(int quantity, int time) {
        this.quantity = quantity;
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private Map<Integer, Product> items;

    public Map<Integer, Product> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Product> items) {
        this.items = items;
    }

    public void addItemsToCaculate(int idProduct) {
        ProductBLO dao = new ProductBLO();
        if (items == null) {
            this.items = new HashMap<>();
        }
        Product dto = dao.getProductByID(idProduct);
        this.items.put(idProduct, dto);
    }

    public void removeItem(int idProduct) {
        if (this.items.containsKey(idProduct)) {
            this.items.remove(idProduct);
        }
    }
    
    public float caculateElectric(float capacity) {

        float totalMoney = 0;
        int f1 = 50, f2 = 50, f3 = 100, f4 = 100, f5 = 100, f6 = 0;
        int m1 = 1510, m2 = 1561, m3 = 1813, m4 = 2282, m5 = 2834, m6 = 2927;
        float usedCapacity = capacity;
        if (usedCapacity <= f1) {
            totalMoney = m1 * usedCapacity;
        } else{
            if (usedCapacity > f1 && usedCapacity <= f1+f2) {
                totalMoney = (m1 * f1) + (m2 * (usedCapacity - f1));
            } else if (usedCapacity > f1+f2 && usedCapacity <= f1+f2+f3) {
                totalMoney = (f1 * m1) + (f2 * m2) + ((usedCapacity- (f1+f2))) * m3;
            } else if (usedCapacity > f1+f2+f3 && usedCapacity <= f1+f2+f3+f4) {
                totalMoney = (f1 * m1) + (f2 * m2) + (f3 * m3) + ((usedCapacity - (f1+f2+f3))*m4);
            } else if (usedCapacity > f1+f2+f3+f4 && usedCapacity <= f1+f2+f3+f4+f5) {
                totalMoney = (f1 * m1) + (f2 * m2) + (f3 * m3) + (f4*m4) + ((usedCapacity - (f1+f2+f3+f4)) * m5);
            }else if(usedCapacity > f1+f2+f3+f4+f5){
                totalMoney = (f1 * m1) + (f2 * m2) + (f3 * m3) + (f4*m4) + (f5*m5) + ((usedCapacity - (f1+f2+f3+f4+f5)) * m6);
            }
        }

        return totalMoney;
    }

}
