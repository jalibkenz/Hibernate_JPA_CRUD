package in.kenz.dao.impl;

import in.kenz.dao.EmployeeDao;
import in.kenz.entity.Employee;
import in.kenz.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {


    @Override
    public void save(Employee employee) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try (entityManager) {


            transaction.begin();

            if (employee.getEmployeeId() == null) {
                // new entity -> persist
                entityManager.persist(employee);
                // employee becomes managed and gets generated id
            } else {
                // existing -> merge (returns managed instance)
                entityManager.merge(employee);
            }

            transaction.commit();

        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) transaction.rollback();
            throw e;
        }
    }


    @Override
    public Employee findById(Long employeeId) {
        try (EntityManager entityManager = JpaUtil.getEntityManager()) {
            return entityManager.find(Employee.class, employeeId);
        }
    }

    //Criteria API
    @Override
    public Employee findByEmployeeName(String employeeName) {
        try (EntityManager entityManager = JpaUtil.getEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

            Root<Employee> root = cq.from(Employee.class);
            cq.select(root).where(cb.equal(root.get("employeeName"), employeeName));

            return entityManager.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    //Criteria API
    @Override
    public List<Employee> findAll() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try (entityManager) {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

            Root<Employee> root = cq.from(Employee.class);
            cq.select(root);  // SELECT e FROM Employee e

            return entityManager.createQuery(cq).getResultList();
        }
    }


    @Override
    public void deleteById(Long employeeId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try (entityManager) {

            transaction.begin();

            //get the employee object
            Employee employeeX = entityManager.find(Employee.class, employeeId);

            //remove employee
            entityManager.remove(employeeX);
            transaction.commit();

            System.out.println("Employee deleted from DB");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); //if transaction commit() didn't close it, then roll back
            throw e;
        }
    }
}
