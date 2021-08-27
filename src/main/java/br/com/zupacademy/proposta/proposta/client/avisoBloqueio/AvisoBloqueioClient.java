package br.com.zupacademy.proposta.proposta.client.avisoBloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AvisoBloqueio", url= "${values.consulta-cartao-url}" )
public interface AvisoBloqueioClient {
    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{id}/bloqueios")
    AvisoBloqueioResponse bloqueia(@PathVariable String id, @RequestBody AvisoBloqueioRequest request);
}