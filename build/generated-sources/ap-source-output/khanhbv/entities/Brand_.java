package khanhbv.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import khanhbv.entities.Product;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-15T14:40:50")
@StaticMetamodel(Brand.class)
public class Brand_ { 

    public static volatile CollectionAttribute<Brand, Product> productCollection;
    public static volatile SingularAttribute<Brand, String> name;
    public static volatile SingularAttribute<Brand, Integer> id;

}