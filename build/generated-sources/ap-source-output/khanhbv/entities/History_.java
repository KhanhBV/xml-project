package khanhbv.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import khanhbv.entities.HistoryProduct;
import khanhbv.entities.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-20T21:36:17")
@StaticMetamodel(History.class)
public class History_ { 

    public static volatile SingularAttribute<History, Date> date;
    public static volatile CollectionAttribute<History, HistoryProduct> historyProductCollection;
    public static volatile SingularAttribute<History, Integer> id;
    public static volatile SingularAttribute<History, User> userID;

}