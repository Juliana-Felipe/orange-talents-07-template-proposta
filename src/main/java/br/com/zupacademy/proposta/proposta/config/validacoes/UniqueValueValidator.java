package br.com.zupacademy.proposta.proposta.config.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Locale;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue anotation) {
        domainAttribute=anotation.fieldName();
        klass= anotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("select 1 from " +klass.getName()+ " where " + domainAttribute.toLowerCase(Locale.ROOT).trim() + " = :value");
        query.setParameter("value".toLowerCase(Locale.ROOT).trim(), value.toString().toLowerCase(Locale.ROOT).trim());
        List<?> list = query.getResultList();
        Assert.state(list.size()<=1, "Foi encontrado mais de um " +klass.getName()+ " com o mesmo atributo " + domainAttribute+ ".");
        return list.isEmpty();
    }
}
