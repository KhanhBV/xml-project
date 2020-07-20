package khanhbv.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import khanhbv.entities.History;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-07-20T21:36:17")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile CollectionAttribute<User, History> historyCollection;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> username;

}