package khanhbv.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import khanhbv.entities.History;
import khanhbv.entities.HistoryProductPK;
import khanhbv.entities.Product;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-23T08:08:44")
@StaticMetamodel(HistoryProduct.class)
public class HistoryProduct_ { 

    public static volatile SingularAttribute<HistoryProduct, HistoryProductPK> historyProductPK;
    public static volatile SingularAttribute<HistoryProduct, Product> product;
    public static volatile SingularAttribute<HistoryProduct, Integer> count;
    public static volatile SingularAttribute<HistoryProduct, History> history;

}