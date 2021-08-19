package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ConsultaCartao", url= "http://localhost:8888/api")
public interface ConsultaCartaoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/cartoes")
    DadosCartaoResponse consultaCartao(@RequestParam Long idProposta);
}
