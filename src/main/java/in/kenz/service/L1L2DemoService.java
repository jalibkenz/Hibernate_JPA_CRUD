package in.kenz.service;

import in.kenz.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class L1L2DemoService {


    public static void demonstrationPurpose(Scanner sc)
    {
        System.out.println( "Hibernate L1 L2 Example" );

        System.out.println("EnterEmployee ID");
        int searchId=sc.nextInt();


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit1");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();

        //create Employee
//        Employee employee=new Employee("Kenz");
//        entityManager.persist(employee);

        //commit transaction
//        transaction.commit();

        //finding Jalib
        //QUERY 1
        Employee employee1 = entityManager.find(Employee.class, searchId);
        System.out.println("🔍SEARCH 1 (Stored in L1 Cache - by EntityManager) \uD83D\uDD14"+employee1);

        //QUERY 2 (Printing from L1 Cache) // No need to run again the query
        System.out.println("🔍SEARCH 2 (Getting from L1 Cache - Same EntityManager) \uD83D\uDD14\uD83D\uDD14"+entityManager.find(Employee.class, searchId));

        //closing manager
        //closing factory

//        //L1 CACHE ||
//        entityManager.close();
//        entityManagerFactory.close();

        //L2 CACHE
        entityManager.close();



        //MANAGER is CLOSED  | ONLY FACTORY OPEN
        //QUERY 3 L1 cache is not shared with another manager, so creating another manager

        EntityManager entityManager55 = entityManagerFactory.createEntityManager();
        System.out.println("🔍SEARCH 3 (Capable of getting from L2 Cache if @cacheable written) \uD83D\uDD14\uD83D\uDD14\uD83D\uDD14"+entityManager55.find(Employee.class, searchId));

        entityManagerFactory.close();



    }
}
