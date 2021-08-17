package br.com.zupacademy.proposta.proposta.proposta.config;

import br.com.zupacademy.proposta.proposta.proposta.Proposta;
import br.com.zupacademy.proposta.proposta.proposta.PropostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Locale;

public class UniqueDocumentValidator implements ConstraintValidator<UniqueDocument, String> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("select 1 from " + Proposta.class.getName() + " p where p.documento = :documento");
        query.setParameter("documento".toLowerCase(Locale.ROOT).trim().replace(".","").replace("-",""), value.toLowerCase(Locale.ROOT).trim().replace(".","").replace("-",""));
        List<?> list = query.getResultList();
        if(list.size() == 1) {throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe proposta para este documento");}
        Assert.state(list.size()<=1, "Foi encontrado mais de uma proposta com o mesmo documento.");
        return list.isEmpty();
    }
}
