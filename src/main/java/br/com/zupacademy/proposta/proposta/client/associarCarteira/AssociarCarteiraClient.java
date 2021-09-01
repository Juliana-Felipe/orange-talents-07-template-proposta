package br.com.zupacademy.proposta.proposta.client.associarCarteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(name = "AssociarCarteira", url= "${values.consulta-cartao-url}" )
public interface AssociarCarteiraClient {


        @RequestMapping(method = RequestMethod.POST, value = "cartoes/{id}/carteiras")
        AssociarCarteiraResponse associa(@PathVariable String id, @RequestBody AssociarCarteiraRequest request);

}
