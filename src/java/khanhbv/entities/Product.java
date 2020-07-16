/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vankhanhbui
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id")
    , @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name")
    , @NamedQuery(name = "Product.findByImageURL", query = "SELECT p FROM Product p WHERE p.imageURL = :imageURL")
    , @NamedQuery(name = "Product.findByUrl", query = "SELECT p FROM Product p WHERE p.url = :url")
    , @NamedQuery(name = "Product.findByPower", query = "SELECT p FROM Product p WHERE p.power = :power")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "imageURL")
    private String imageURL;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @Column(name = "power")
    private float power;
    @JoinColumn(name = "brandID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Brand brandID;
    @JoinColumn(name = "categoryID", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category categoryID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<HistoryProduct> historyProductCollection;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, String imageURL, String url, float power) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.url = url;
        this.power = power;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public Brand getBrandID() {
        return brandID;
    }

    public void setBrandID(Brand brandID) {
        this.brandID = brandID;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    @XmlTransient
    public Collection<HistoryProduct> getHistoryProductCollection() {
        return historyProductCollection;
    }

    public void setHistoryProductCollection(Collection<HistoryProduct> historyProductCollection) {
        this.historyProductCollection = historyProductCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "khanhbv.entities.Product[ id=" + id + " ]";
    }
    
}
