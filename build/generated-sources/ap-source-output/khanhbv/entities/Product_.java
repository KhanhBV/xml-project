package khanhbv.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import khanhbv.entities.Brand;
import khanhbv.entities.Category;
import khanhbv.entities.HistoryProduct;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-19T01:27:53")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile CollectionAttribute<Product, HistoryProduct> historyProductCollection;
    public static volatile SingularAttribute<Product, String> imageURL;
    public static volatile SingularAttribute<Product, Brand> brandID;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, Float> power;
    public static volatile SingularAttribute<Product, String> url;
    public static volatile SingularAttribute<Product, Category> categoryID;

}