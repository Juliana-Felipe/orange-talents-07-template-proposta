package br.com.zupacademy.proposta.proposta.client.avisoAvisoDeViagem;

import br.com.zupacademy.proposta.proposta.client.avisoBloqueio.AvisoBloqueioRequest;
import br.com.zupacademy.proposta.proposta.client.avisoBloqueio.AvisoBloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Aviso", url= "${values.consulta-cartao-url}" )
public interface AvisoDeViagemClient {
    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{id}/avisos")
    AvisoDeViagemResponse notifica(@PathVariable String id, @RequestBody AvisoDeViagemRequest request);
}