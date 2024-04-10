package org.thefinal.jparelationships.Dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.thefinal.jparelationships.Employee;
import org.thefinal.jparelationships.SearchRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class EmployeeDao {

    private final EntityManager entityManager;

    public EmployeeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Employee>findAllBySimpleQuery(
            String firstName,
    String lastName,
    String email
    ){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee>criteriaQuery=criteriaBuilder.createQuery(Employee.class);

        //select* from employee
        Root<Employee> root=criteriaQuery.from(Employee.class);
        //prepare where clause
        //where firtName like "%takue%
        Predicate firstNamePredicate= (Predicate) criteriaBuilder
                .like(root.get("firstName"),"%"+firstName+"%");
        Predicate lastNamePredicate= (Predicate) criteriaBuilder
                .like(root.get("lastName"),"%"+lastName+"%");
        Predicate emailNamePredicate= (Predicate) criteriaBuilder
                .like(root.get("email"),"%"+email+"%");

        Predicate firstNameOrLastNameorPredicate= (Predicate) criteriaBuilder.or(
                (jakarta.persistence.criteria.Predicate) firstNamePredicate,
                (jakarta.persistence.criteria.Predicate) lastNamePredicate,
                (jakarta.persistence.criteria.Predicate) emailNamePredicate
        );
        //final query select * from employee where firstName like %takue%
        //criteriaQuery.where((Expression<Boolean>) firstNameOrLastNameOrPredicate);
       var andEmailPredicate= criteriaBuilder.and((Expression<Boolean>) firstNameOrLastNameorPredicate, (Expression<Boolean>) emailNamePredicate);

        criteriaQuery.where(
                andEmailPredicate
                  );
        TypedQuery<Employee>query=entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Employee> findAllByCriteria(
            SearchRequest request ){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee>criteriaQuery= criteriaBuilder.createQuery(Employee.class);
        List<Predicate>predicates=new ArrayList<>();

        //select from employee
        Root<Employee>root=criteriaQuery.from(Employee.class);
        if(request.getFirstName()!=null) {
            Predicate firstNamePredicate = (Predicate) criteriaBuilder.
                    like(root.get("firstName"), "%" + request.getFirstName() + "%");
            predicates.add(firstNamePredicate);
        }
            if(request.getFirstName()!=null) {
                Predicate lastNamePredicate = (Predicate) criteriaBuilder.
                        like(root.get("lastName"), "%" + request.getLastName() + "%");
                predicates.add(lastNamePredicate);
            }
                if(request.getFirstName()!=null){
                    Predicate emailPredicare = (Predicate) criteriaBuilder.
                            like(root.get("email"), "%"+request.getEmail()+"%");
                    predicates.add(emailPredicare);
        }
                criteriaQuery.where(
                        criteriaBuilder.or(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]))
                );
                TypedQuery<Employee>query=entityManager.createQuery(criteriaQuery);

                return query.getResultList();
    }

}
