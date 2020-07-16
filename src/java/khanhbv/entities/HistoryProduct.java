/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vankhanhbui
 */
@Entity
@Table(name = "history_product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryProduct.findAll", query = "SELECT h FROM HistoryProduct h")
    , @NamedQuery(name = "HistoryProduct.findByHistoryID", query = "SELECT h FROM HistoryProduct h WHERE h.historyProductPK.historyID = :historyID")
    , @NamedQuery(name = "HistoryProduct.findByProductID", query = "SELECT h FROM HistoryProduct h WHERE h.historyProductPK.productID = :productID")
    , @NamedQuery(name = "HistoryProduct.findByCount", query = "SELECT h FROM HistoryProduct h WHERE h.count = :count")})
public class HistoryProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistoryProductPK historyProductPK;
    @Basic(optional = false)
    @Column(name = "count")
    private int count;
    @JoinColumn(name = "historyID", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private History history;
    @JoinColumn(name = "productID", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public HistoryProduct() {
    }

    public HistoryProduct(HistoryProductPK historyProductPK) {
        this.historyProductPK = historyProductPK;
    }

    public HistoryProduct(HistoryProductPK historyProductPK, int count) {
        this.historyProductPK = historyProductPK;
        this.count = count;
    }

    public HistoryProduct(int historyID, int productID) {
        this.historyProductPK = new HistoryProductPK(historyID, productID);
    }

    public HistoryProductPK getHistoryProductPK() {
        return historyProductPK;
    }

    public void setHistoryProductPK(HistoryProductPK historyProductPK) {
        this.historyProductPK = historyProductPK;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historyProductPK != null ? historyProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryProduct)) {
            return false;
        }
        HistoryProduct other = (HistoryProduct) object;
        if ((this.historyProductPK == null && other.historyProductPK != null) || (this.historyProductPK != null && !this.historyProductPK.equals(other.historyProductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "khanhbv.entities.HistoryProduct[ historyProductPK=" + historyProductPK + " ]";
    }
    
}
