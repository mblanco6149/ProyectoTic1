package tic1.grupo9.facheritApp.backend.services;

import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import tic1.grupo9.facheritApp.commons.entities.Clothes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Builder
public class ClotheSpecification implements Specification<Clothes> {

    private String gender;
    private String type;


    @Override
    public Predicate toPredicate(Root<Clothes> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(gender!=null){
            criteriaBuilder.equal(root.get("gender"), gender);
        }if(type!=null){
            criteriaBuilder.equal(root.get("type"), type);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
