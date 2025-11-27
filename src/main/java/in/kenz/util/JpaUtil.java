package in.kenz.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    public static EntityManagerFactory entityManagerFactory;
    static {
         entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit1");
    }

    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();

    }

    public static void close(){
        entityManagerFactory.close();
    }

}
