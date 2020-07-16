/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vankhanhbui
 */
@Embeddable
public class HistoryProductPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "historyID")
    private int historyID;
    @Basic(optional = false)
    @Column(name = "productID")
    private int productID;

    public HistoryProductPK() {
    }

    public HistoryProductPK(int historyID, int productID) {
        this.historyID = historyID;
        this.productID = productID;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) historyID;
        hash += (int) productID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryProductPK)) {
            return false;
        }
        HistoryProductPK other = (HistoryProductPK) object;
        if (this.historyID != other.historyID) {
            return false;
        }
        if (this.productID != other.productID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "khanhbv.entities.HistoryProductPK[ historyID=" + historyID + ", productID=" + productID + " ]";
    }
    
}
